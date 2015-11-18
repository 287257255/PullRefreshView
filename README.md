PullRefreshView  
===================================
操作过程不引起控件重新计算尺寸和重新布局，只发生重绘 所以滑动过程流畅。合理触摸事件分发过程，减少无关操作的执行 
    
FlingLayout
-----------------------------------  
实现了弹性拖动，支持任意控件。
	
![](https://raw.githubusercontent.com/Y-bao/PullRefreshView/master/GIF/1.gif) 
 
PullRefreshLayout
-----------------------------------  
继承自FlingLayout，具有弹性拖动，配合BaseHeaderView，BaseFooterView 实现经典的 下拉刷新，上拉加载的功能，支持任意控件。不使用BaseHeaderView，BaseFooterView时功能和FlingLayout相同。	
	
![](https://raw.githubusercontent.com/Y-bao/PullRefreshView/master/GIF/2.gif) 


RGPullRefreshLayout
-----------------------------------  
PullRefreshLayout 在其基础上实现，下拉刷新和上拉加载 滑出方式的选择。
	
![](https://raw.githubusercontent.com/Y-bao/PullRefreshView/master/GIF/3.gif)
![](https://raw.githubusercontent.com/Y-bao/PullRefreshView/master/GIF/4.gif)
![](https://raw.githubusercontent.com/Y-bao/PullRefreshView/master/GIF/5.gif) 

		public final static int LAYOUT_NORMAL = 0x00;
		public final static int LAYOUT_DRAWER = 0x01;
		public final static int LAYOUT_SCROLLER = 0x10;

其它图片展示
-----------------------------------  
![](https://raw.githubusercontent.com/Y-bao/PullRefreshView/master/GIF/6.gif) 
