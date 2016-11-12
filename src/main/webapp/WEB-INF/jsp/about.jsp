<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>关于-WJY的笔记本</title>

<!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath }/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="${pageContext.request.contextPath }/css/blog-post.css"
	rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"
					href="${pageContext.request.contextPath }/index">WJY的笔记本</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath }/about" class="active">关于</a></li>
					<li><a href="${pageContext.request.contextPath }/feedback/save">反馈</a></li>
					<li><a href="${pageContext.request.contextPath }/link/save">申请友链</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>
	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<!-- Blog Post Content Column -->
			<div class="col-md-8">
				<h1>关于我</h1>
				<h2>基本信息</h2>
				<span style="font-size: 16px">温景毅（Paul），大三学生（2016-2017），现就读于<a
					href="http://baike.baidu.com/link?url=Da8RrwqgTkt2i6U9MEEu5G2ssszrtVyAvalwZuPNSbkr4mnkb24Jc80WN3bmK2LtC9kCD_I6uhEy2NmM-a2IGb9NSaJK0EDgq2GusK1OgASnsoiJSh65rFvLSaRx38UzobFULrWssqyE6gDImUCFjTZ1pHK0m7JvM7RPObE-d6HxIy1QAZTjT0VCIiXAxL2x"
					target="_blank">仲恺农业工程学院</a>，信息科学与技术学院，计算机科学与技术专业,准菜鸟程序猿一枚。
				</span>
				<h2>常用程序语言</h2>
				<span style="font-size: 16px">java，c/cpp</span>
				<h2>联系方式</h2>
				<span style="font-size: 16px"> 邮箱 ： wen229267643@gmail.com<br />
					微信 ： v229267643<br /> github：https://github.com/gh-paulwen<br />
					欢迎骚扰
				</span>
				<h1>
					<br /> 关于本网站
				</h1>
				<h2>网站的名字</h2>
				<span style="font-size: 16px;">
					WJY的笔记本，WJY是我名字的首字母。而笔记本则是这个网站的准确定位，这就是我的笔记本，同时显得老实，谦逊。<br>
				</span>
				<h2>域名的选择</h2>
				<span style="font-size: 16px;"> www.wengjingyi.top ，
					wengjingyi是我名字的全拼，选择.top后缀完全是因为便宜。<br>
				</span>
				<h2>本网站的github地址</h2>
				<a href="https://github.com/gh-paulwen/yiblog-maven" target="_blank">第一版</a>（s2sh）<br>
				<a href="https://github.com/gh-paulwen/yiblog-ssm" target="_blank">第二版</a>（ssm）<br><br> 
				<span style="font-size: 16px">这个网站是用ssh框架所搭建的一个记录学习笔记的东西，由于自己的前端水平的确是菜，所以页面看起来不是那么好看（怪自己不太会审美，总之简洁就好）。有需要的话，后期可能会找个bootstrap的模板套上算了。<br />
					搭这个网站是大二末期做数据库课程设计的时候产生的想法，目的主要有一下几个：
				</span>
				<ol>
					<li><span style="font-size: 16px">检验自己的学习成果。</span></li>
					<li><span style="font-size: 16px">用于记下自己的学习笔记，学了这么久发现自己并没有做学习笔记，这是一个bug。</span></li>
					<li><span style="font-size: 16px">回顾，整理以前所学知识。</span></li>
					<li><span style="font-size: 16px">希望认识到更多志同道合的人吧。</span></li>
					<li><span style="font-size: 16px">对自己会是一种激励。带着充实这个博客的心，我想我会更有动力去学习更多的东西。</span></li>
					<li><span style="font-size: 16px">练一下文笔和表达。这的确是一大短板。</span></li>
				</ol>
				<span style="font-size: 16px"> 博客内容仅仅是个人的见解和总结，如果有错误的话请指正。 </span>
				<h1>关于捐赠</h1>
				<span style="font-size: 16px">众多类似的博客都有设置捐赠这东西，我也有考虑要不要。鉴于阿里云的服务器有学生优惠，良心，就没有了捐赠的需求。</span>
				<br /> <br />
				<h1>大三老鸟的内心想法</h1>
				<span style="font-size: 16px">
					大学（我想说我所在的学校），很坑的就是学校所设的课程实用性真的不强（如组成原理，电路等），对于参加工作帮助不大，尤其是大学毕业之后都忘光了。<br>
					实用的要数，c/c++，数据库等。但是老师都是教点皮毛而已，真是"师傅带进门，修行靠个人"。<br>
					我之所以能搭建这个网站，全都是自学的，虽然并不是很完善很合理，但是能满足记笔记，分享这些知识的需求。未来，自己也一定会不断努力，学习更多的知识、技能，才可以在大四找份好实习，毕业后找份好工。<br>
				</span> <br>
			</div>
			<!-- Blog Sidebar Widgets Column -->
			<div class="col-md-4">
			    <div class="well">
				<div class="well">
					<h4>搜索</h4>
					<form id="search_form" action="${pageContext.request.contextPath }/passage/search" method="get">
					<div class="input-group">
						<input name="content" type="text" class="form-control" >
						<span class="input-group-btn">
						<button id="btn_submit" class="btn btn-default" type="button">
							<span class="glyphicon glyphicon-search"></span>
						</button>
						</span>
					</div>
					</form>
					<!-- /.input-group -->
				</div>
				<!-- Blog Categories Well -->
				<div class="well">
					<h4>文章分类</h4>
					<div class="row">
						<div class="col-lg-6">
							<ul class="list-unstyled">
								<c:forEach items="${requestScope.listSubCategory }"
									var="subCategory" begin="0" end="${requestScope.halfOfCates}">
									<li><a href="${pageContext.request.contextPath }/passage/page/${subCategory.id}/1">${subCategory.name }</a>&nbsp;(${subCategory.passageCount })</li>
								</c:forEach>
							</ul>
						</div>
						<!-- /.col-lg-6 -->
						<div class="col-lg-6">
							<ul class="list-unstyled">
								<c:forEach items="${requestScope.listSubCategory }"
									var="subCategory" begin="${requestScope.halfOfCates + 1}">
									<li><a href="${pageContext.request.contextPath }/passage/page/${subCategory.id}/1">${subCategory.name }</a>&nbsp;(${subCategory.passageCount })</li>
								</c:forEach>
							</ul>
						</div>
						<!-- /.col-lg-6 -->
					</div>
					<!-- /.row -->
				</div>
				</div>
				
				<!-- Link Well -->
				<div class="well">
				  <h4>推荐网站</h4>
				  <h4></h4>
				  <c:forEach items="${listLink}" var="link">
				    <h4><a target="_blank" href="${link.url }"><c:out value="${link.name }"/></a></h4>
				  </c:forEach>
				</div>
				
				<!-- Side Widget Well -->
				<div class="well">
					<h4>${build.content } : <fmt:formatDate value="${build.time }" pattern="yyyy年MM月dd日"/></h4>
					<h4>${lastUpdate.content } : <fmt:formatDate value="${lastUpdate.time }" pattern="yyyy年MM月dd日"/></h4>
				</div>
			</div>
		</div>
		<!-- /.row -->
		<hr>
		<!-- Footer -->
		<footer>
			<div class="row">
				<div class="col-lg-12">
					<p>Copyright &copy; WJY的笔记本</p>
				</div>
			</div>
			<!-- /.row -->
		</footer>

	</div>
	<!-- /.container -->

	<!-- jQuery -->
	<script src="${pageContext.request.contextPath }/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<script type="text/javascript">
	  $(document).ready(function(){
		  $("#btn_submit").click(function(){
			  $("#search_form").submit();
		  });
	  });
	</script>
	<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1260575777'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1260575777%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));</script>
	<script>
		(function(){
			var bp = document.createElement('script');
	    	var curProtocol = window.location.protocol.split(':')[0];
	    	if (curProtocol === 'https') {
	        	bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';        
	    	}
	    	else {
	        	bp.src = 'http://push.zhanzhang.baidu.com/push.js';
	    	}
	    	var s = document.getElementsByTagName("script")[0];
	    	s.parentNode.insertBefore(bp, s);
		})();
	</script>
</body>

</html>
