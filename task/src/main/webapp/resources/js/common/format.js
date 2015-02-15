/*$('.llbTable').each(function(){            
		var html = "<tr>";//</tr> 
		$('tr', this).each(function(){
			var sum = 0;            
			$('td', this).each(function(){   
				var attr = $(this).attr("render");  
				if(attr && attr.length>0){      
					if(Number($(this)[0].innerHTML.replace(new RegExp(",", "g"), '')) >= 0){              
						sum += Number($(this)[0].innerHTML.replace(new RegExp(",", "g"), ''));                
					}
				};  
			});
			html += "<td>" + XyFormat.money(sum) +"</td>";
		});      
		html += "</tr>";
		$(this).append(html);        
	});    */
XyFormat = function() {
	var trimRe = /^\s+|\s+$/g;
	return {
		ellipsis : function(value, len) {
			if (value && value.length > len) {
				return value.substr(0, len - 3) + "..."
			}
			return value
		},
		undef : function(value) {
			return value !== undefined ? value : ""
		},
		defaultValue : function(value, defaultValue) {
			return value !== undefined && value !== "" ? value : defaultValue
		},
		htmlEncode : function(value) {
			return !value ? value : String(value).replace(/&/g, "&amp;")
					.replace(/>/g, "&gt;").replace(/</g, "&lt;").replace(/"/g,
							"&quot;")
		},
		htmlDecode : function(value) {
			return !value ? value : String(value).replace(/&amp;/g, "&")
					.replace(/&gt;/g, ">").replace(/&lt;/g, "<").replace(
							/&quot;/g, "\"")
		},
		trim : function(value) {
			return String(value).replace(trimRe, "")
		},
		substr : function(value, start, length) {
			return String(value).substr(start, length)
		},
		lowercase : function(value) {
			return String(value).toLowerCase()
		},
		uppercase : function(value) {
			return String(value).toUpperCase()
		},
		capitalize : function(value) {
			return !value ? value : value.charAt(0).toUpperCase()
					+ value.substr(1).toLowerCase()
		},
		call : function(value, fn) {
			if (arguments.length > 2) {
				var args = Array.prototype.slice.call(arguments, 2);
				args.unshift(value);
				return eval(fn).apply(window, args)
			} else {
				return eval(fn).call(window, value)
			}
		},
		money : function(v) {
			if (isNaN(v)) {
				return "0.00";
			} else {
				v = (Math.round((v - 0) * 100)) / 100;
				v = (v == Math.floor(v)) ? v + ".00" : ((v * 10 == Math.floor(v
						* 10)) ? v + "0" : v);
				v = String(v);
				var ps = v.split(".");
				var whole = ps[0];
				var sub = ps[1] ? "." + ps[1] : ".00";
				var r = /(\d+)(\d{3})/;
				while (r.test(whole)) {
					whole = whole.replace(r, "$1" + "," + "$2")
				}
				v = whole + sub;
				if (v.charAt(0) == "-") {
					return "-" + v.substr(1)
				}
				return "" + v
			}
		},
		overFlowTip : function(val) {
			return "<span stype='display:table;width:100%;' title='" + val
					+ "'>" + val + "</span>";
		},
		date : function(v, format) {
			if (!v) {
				return ""
			}
			if (!Ext.isDate(v)) {
				v = v.replace(/-/g, "/");
				v = new Date(Date.parse(v))
			}
			return v.dateFormat(format || "m/d/Y")
		},
		dateRenderer : function(format) {
			return function(v) {
				return XyFormat.date(v, format)
			}
		},
		stripTagsRE : /<\/?[^>]+>/gi,
		stripTags : function(v) {
			return !v ? v : String(v).replace(this.stripTagsRE, "")
		},
		stripScriptsRe : /(?:<script.*?>)((\n|\r|.)*?)(?:<\/script>)/ig,
		stripScripts : function(v) {
			return !v ? v : String(v).replace(this.stripScriptsRe, "")
		},
		fileSize : function(size) {
			if (size < 1024) {
				return size + " bytes"
			} else {
				if (size < 1048576) {
					return (Math.round(((size * 10) / 1024)) / 10) + " KB"
				} else {
					return (Math.round(((size * 10) / 1048576)) / 10) + " MB"
				}
			}
		},
		math : function() {
			var fns = {};
			return function(v, a) {
				if (!fns[a]) {
					fns[a] = new Function("v", "return v " + a + ";")
				}
				return fns[a](v)
			}
		}()
	}
}();


(function($) {

    $.formatCurrency = {};

    $.formatCurrency.regions = [];

    // default Region is en
    $.formatCurrency.regions[''] = {
        symbol: '',
        positiveFormat: '%s%n',
        negativeFormat: '(-%s%n)',
        decimalSymbol: '.',
        digitGroupSymbol: ',',
        groupDigits: true
    };

    $.fn.formatCurrency = function(destination, settings) {

        if (arguments.length == 1 && typeof destination !== "string") {
            settings = destination;
            destination = false;
        }

        // initialize defaults
        var defaults = {
            name: "formatCurrency",
            colorize: false,
            region: '',
            global: true,
            roundToDecimalPlace: 2, // roundToDecimalPlace: -1; for no rounding; 0 to round to the dollar; 1 for one digit cents; 2 for two digit cents; 3 for three digit cents; ...
            eventOnDecimalsEntered: false
        };
        // initialize default region
        defaults = $.extend(defaults, $.formatCurrency.regions['']);
        // override defaults with settings passed in
        settings = $.extend(defaults, settings);

        // check for region setting
        if (settings.region.length > 0) {
            settings = $.extend(settings, getRegionOrCulture(settings.region));
        }
        settings.regex = generateRegex(settings);

        return this.each(function() {
            $this = $(this);

            // get number
            var num = '0';
            num = $this[$this.is('input, select, textarea') ? 'val' : 'html']();

            //identify '(123)' as a negative number
            if (num.search('\\(') >= 0) {
                num = '-' + num;
            }

            if (num === '' || (num === '-' && settings.roundToDecimalPlace === -1)) {
                return;
            }

            // if the number is valid use it, otherwise clean it
            if (isNaN(num)) {
                // clean number
                num = num.replace(settings.regex, '');
                
                if (num === '' || (num === '-' && settings.roundToDecimalPlace === -1)) {
                    return;
                }
                
                if (settings.decimalSymbol != '.') {
                    num = num.replace(settings.decimalSymbol, '.');  // reset to US decimal for arithmetic
                }
                if (isNaN(num)) {
                    num = '0';
                }
            }
            
            // evalutate number input
            var numParts = String(num).split('.');
            var isPositive = (num == Math.abs(num));
            var hasDecimals = (numParts.length > 1);
            var decimals = (hasDecimals ? numParts[1].toString() : '0');
            var originalDecimals = decimals;
            
            // format number
            num = Math.abs(numParts[0]);
            num = isNaN(num) ? 0 : num;
            if (settings.roundToDecimalPlace >= 0) {
                decimals = parseFloat('1.' + decimals); // prepend "0."; (IE does NOT round 0.50.toFixed(0) up, but (1+0.50).toFixed(0)-1
                decimals = decimals.toFixed(settings.roundToDecimalPlace); // round
                if (decimals.substring(0, 1) == '2') {
                    num = Number(num) + 1;
                }
                decimals = decimals.substring(2); // remove "0."
            }
            num = String(num);

            if (settings.groupDigits) {
                for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++) {
                    num = num.substring(0, num.length - (4 * i + 3)) + settings.digitGroupSymbol + num.substring(num.length - (4 * i + 3));
                }
            }

            if ((hasDecimals && settings.roundToDecimalPlace == -1) || settings.roundToDecimalPlace > 0) {
                num += settings.decimalSymbol + decimals;
            }

            // format symbol/negative
            var format = isPositive ? settings.positiveFormat : settings.negativeFormat;
            var money = format.replace(/%s/g, settings.symbol);
            money = money.replace(/%n/g, num);

            // setup destination
            var $destination = $([]);
            if (!destination) {
                $destination = $this;
            } else {
                $destination = $(destination);
            }
            // set destination
            $destination[$destination.is('input, select, textarea') ? 'val' : 'html'](money);

            if (
                hasDecimals && 
                settings.eventOnDecimalsEntered && 
                originalDecimals.length > settings.roundToDecimalPlace
            ) {
                $destination.trigger('decimalsEntered', originalDecimals);
            }

            // colorize
            if (settings.colorize) {
                $destination.css('color', isPositive ? 'black' : 'red');
            }
        });
    };

    // Remove all non numbers from text
    $.fn.toNumber = function(settings) {
        var defaults = $.extend({
            name: "toNumber",
            region: '',
            global: true
        }, $.formatCurrency.regions['']);

        settings = jQuery.extend(defaults, settings);
        if (settings.region.length > 0) {
            settings = $.extend(settings, getRegionOrCulture(settings.region));
        }
        settings.regex = generateRegex(settings);

        return this.each(function() {
            var method = $(this).is('input, select, textarea') ? 'val' : 'html';
            $(this)[method]($(this)[method]().replace('(', '(-').replace(settings.regex, ''));
        });
    };

    // returns the value from the first element as a number
    $.fn.asNumber = function(settings) {
        var defaults = $.extend({
            name: "asNumber",
            region: '',
            parse: true,
            parseType: 'Float',
            global: true
        }, $.formatCurrency.regions['']);
        settings = jQuery.extend(defaults, settings);
        if (settings.region.length > 0) {
            settings = $.extend(settings, getRegionOrCulture(settings.region));
        }
        settings.regex = generateRegex(settings);
        settings.parseType = validateParseType(settings.parseType);

        var method = $(this).is('input, select, textarea') ? 'val' : 'html';
        var num = $(this)[method]();
        num = num ? num : "";
        num = num.replace('(', '(-');
        num = num.replace(settings.regex, '');
        if (!settings.parse) {
            return num;
        }

        if (num.length == 0) {
            num = '0';
        }

        if (settings.decimalSymbol != '.') {
            num = num.replace(settings.decimalSymbol, '.');  // reset to US decimal for arthmetic
        }

        return window['parse' + settings.parseType](num);
    };

    function getRegionOrCulture(region) {
        var regionInfo = $.formatCurrency.regions[region];
        if (regionInfo) {
            return regionInfo;
        }
        else {
            if (/(\w+)-(\w+)/g.test(region)) {
                var culture = region.replace(/(\w+)-(\w+)/g, "$1");
                return $.formatCurrency.regions[culture];
            }
        }
        // fallback to extend(null) (i.e. nothing)
        return null;
    }

    function validateParseType(parseType) {
        switch (parseType.toLowerCase()) {
            case 'int':
                return 'Int';
            case 'float':
                return 'Float';
            default:
                throw 'invalid parseType';
        }
    }
    
    function generateRegex(settings) {
        if (settings.symbol === '') {
            return new RegExp("[^\\d" + settings.decimalSymbol + "-]", "g");
        }
        else {
            var symbol = settings.symbol.replace('$', '\\$').replace('.', '\\.');        
            return new RegExp(symbol + "|[^\\d" + settings.decimalSymbol + "-]", "g");
        }    
    }

})(jQuery);