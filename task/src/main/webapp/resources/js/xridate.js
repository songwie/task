 //---------------------------------------------------   // 日期格式化   // 格式 YYYY/yyyy/YY/yy 表示年份   // MM/M 月份   // W/w 星期   // dd/DD/d/D 日期   // hh/HH/h/H 时间   // mm/m 分钟   // ss/SS/s/S 秒   //---------------------------------------------------   
Date.prototype.format = function(formatStr)    {       
	var str = formatStr;        
	var Week = ['日','一','二','三','四','五','六'];           
	str=str.replace(/yyyy|YYYY/,this.getFullYear());        
	str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));            
	str=str.replace(/MM/,this.getMonth()>9?this.getMonth().toString():'0' + this.getMonth());        
	str=str.replace(/M/g,this.getMonth());            
	str=str.replace(/w|W/g,Week[this.getDay()]);            
	str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());        
	str=str.replace(/d|D/g,this.getDate());            
	str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());        
	str=str.replace(/h|H/g,this.getHours());        
	str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());        
	str=str.replace(/m/g,this.getMinutes());            
	str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());        
	str=str.replace(/s|S/g,this.getSeconds());            
	
	return str;   
}       
   