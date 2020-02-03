<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/resources/platform/inc.jsp"%>
<%@ page language="java" import="com.samton.platform.framework.util.CurrentUtil"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit">
	<script type="text/javascript">
		
	</script>
	</head>
	
	<body>
		<div class="main_con mCustomScrollbar_y" data-mcs-theme="dark">
			<div class="main_piece">
				<div class="customer_nav">
					<ul>
						<li>
							<div class="customer_con newbusiness">
								<div class="customer_img">
									<i></i>
								</div>
								<div class="customer_info">
									<span>50</span>
									<p>今日新增企业</p>
								</div>
							</div>
						</li>
						<li>
							<div class="customer_con totalnum">
								<div class="customer_img">
									<i></i>
								</div>
								<div class="customer_info">
									<span>3481</span>
									<p>企业总数</p>
								</div>
							</div>
						</li>
						<li>
							<div class="customer_con newagents">
								<div class="customer_img">
									<i></i>
								</div>
								<div class="customer_info">
									<span>8</span>
									<p>今日新增代理商</p>
								</div>
							</div>
						</li>
						<li>
							<div class="customer_con totalagents">
								<div class="customer_img">
									<i></i>
								</div>
								<div class="customer_info">
									<span>7</span>
									<p>代理商总数</p>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
			<div class="main_piece">
				<div class="chart_con width50">
					<div class="chart_tit">
						<h2>
							<i class="midicon trafficrecha_icon"></i><span>XXXX占比图（单位：MB）</span>
						</h2>
						<div class="dropdown">
							<a id="dLabel" href="javascript:void(0);" class="droptit" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								<span class="text">本周</span> <span class="caret"> <i class="arrow"></i> </span>
							</a>
							<ul class="dropmenu" aria-labelledby="dLabel">
								<li class="current"><a href="javascript:void(0);">本周</a></li>
								<li><a href="javascript:void(0);">本月</a></li>
							</ul>
						</div>
					</div>
					<div class="chart_info">
						<img src="${ctx.path}/resources/images/chart_img_136.jpg" alt="img" />
					</div>
				</div>
				<div class="chart_con width50">
					<div class="chart_tit">
						<h2>
							<i class="midicon regionaldistr_icon"></i><span>XXXX占比图（单位：MB）</span>
						</h2>
						<div class="dropdown">
							<a id="dLabel3" class="droptit" href="javascript:"
								data-toggle="dropdown"> <span class="text">中国移动</span> <span
								class="caret"> <i class="arrow"></i>
							</span>
							</a>
							<ul class="dropmenu" aria-labelledby="dLabel3">
								<li class="current"><a href="javascript:void(0);">中国移动</a></li>
								<li><a href="javascript:void(0);">中国联通</a></li>
								<li><a href="javascript:void(0);">中国电信</a></li>
							</ul>
						</div>
						<div class="dropdown">
							<a id="dLabel2" class="droptit" href="javascript:" data-toggle="dropdown"> 
							<span class="text">本周</span> <span class="caret"> <i class="arrow"></i> </span>
							</a>
							<ul class="dropmenu" aria-labelledby="dLabe2l">
								<li class="current"><a href="javascript:void(0);">本周</a></li>
								<li><a href="javascript:void(0);">本月</a></li>
							</ul>
						</div>
					</div>
					<div class="chart_info">
						<img src="${ctx.path}/resources/images/chart_img_138.jpg" alt="img" />
					</div>
				</div>
			</div>
			<div class="main_piece">
				<div class="chart_con">
					<div class="chart_tit">
						<h2>
							<i class="midicon accounting_icon"></i><span>XXXX占比图（单位：MB）</span>
						</h2>
						<div class="dropdown">
							<a id="dLabel2" class="droptit" href="javascript:" data-toggle="dropdown"> 
							<span class="text">本周</span> <span class="caret"> <i class="arrow"></i> </span>
							</a>
							<ul class="dropmenu" aria-labelledby="dLabe2l">
								<li class="current"><a href="javascript:void(0);">本周</a></li>
								<li><a href="javascript:void(0);">本月</a></li>
							</ul>
						</div>
					</div>
					<div class="chart_info">
						<img src="${ctx.path}/resources/images/chart_img_05.jpg" alt="img" />
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
