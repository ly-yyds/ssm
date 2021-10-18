package com.mage.util;
/**
 * 生成分页代码
 * 	tagetUrl 目标地址
 *	totalNum 总记录数
 *	currentPage 当前页
 *	pageSize 每页大小
 */
public class PageUtil {
	public static String getPageCode(String tagetUrl,long totalNum, int currentPage ,int pageSize) {
		// 总页码数 = 总记录数 % 每页大小 == 0 ? 整除 : 不能整除+1
		long totalPage = totalNum % pageSize == 0 ? totalNum/pageSize : totalNum / pageSize +1;
		if(totalPage==0) {
			return "未查询到数据";
		}else {
			StringBuffer pageCode = new StringBuffer();
			// 首页 ：直接显示
			pageCode.append("<li><a href='" + tagetUrl + "&page=1'>首页</a></li>");
			// 上一页 ：当前页不是第一页的时候显示
			if(currentPage>1) {
				pageCode.append("<li><a href='" +tagetUrl + "&page=" + (currentPage-1) + "'>上一页</a></li>");
			}
			// 页码 取5个数，但哪个显示得判断
			for(int i = currentPage-2; i <= currentPage+2; i++ ) {
				// 页码小于1或者大于总页数不显示
				if(i<1||i>totalPage) {
					continue;// 掉到下一次循环中
				}
				// 能显示得页码数，判断是不是当前页，当前页不能点击
				if(i==currentPage) {
					// 只是显示不做别的
					pageCode.append("<li>" + i + "</li>");
				}else {
					// 否则不是当前页面可以点击
					pageCode.append("<li><a href='" + tagetUrl + "&page=" + i + "'>" + i + "</a></li>");
				}
			}
			// 下一页：当前页小于总页数的时候显示
			if(currentPage<totalPage) {
				pageCode.append("<li><a href='" + tagetUrl + "&page=" + (currentPage+1) + "'>下一页</a></li>");
			}
			// 尾页：直接显示
			pageCode.append("<li><a href='" + tagetUrl + "&page=" + totalPage + "'>尾页</a></li>");
			return pageCode.toString();
		}	
	}
}