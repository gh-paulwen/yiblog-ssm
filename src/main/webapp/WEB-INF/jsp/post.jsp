<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title>${passage.title }-WJY的笔记本</title>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/ico.jpg">
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css"  rel="stylesheet">
    <link href="http://libs.baidu.com/fontawesome/4.0.3/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/css/blog-post.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/css/common.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/css/passage.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/css/wangEditor.min.css" rel="stylesheet">

</head>
<body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath }/index">WJY的笔记本</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath }/about">关于</a></li>
                    <li><a href="${pageContext.request.contextPath }/feedback/save">反馈</a></li>
                    <li><a href="${pageContext.request.contextPath }/link/save">申请友链</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <h1>${passage.title }</h1>
                <p class="lead">
                    by <a href="#">${passage.author.name }</a>
                </p>
                <p class="lead">
                    分类:<a href="${pageContext.request.contextPath }/passage/page/${passage.subCategory.id}/1">${passage.subCategory.name }</a>
                </p>
                <hr>
                <p><span class="glyphicon glyphicon-time"></span> 发布于 <fmt:formatDate value="${passage.writetime }" pattern="yyyy年MM月dd日"/> </p>
                <hr>
                  ${passage.content }
                <hr>

                <div class="well">
                    <form:form action="${pageContext.request.contextPath }/comment/submitSave" method="POST" modelAttribute="comment">
                        <form:hidden path="passage" />
                        <div class="form-group">
                            <h4>评论 :</h4>
                            <form:textarea path="content" class="form-control" rows="12" placeholder="评论内容"></form:textarea>
                            <h4></h4>
                            <form:input class="form-control" placeholder="名字（选填）" path="fromUser"/>
                        </div>
                        <input type="submit" class="btn btn-primary" value="提交">
                    </form:form>
                </div>
                <hr>

				<c:forEach items="${mapCommentReply }" var="cr">
				<div class="media">
                    <div class="media-body">
                        <h4 class="media-heading"><c:out value="${cr.key.fromUser }"></c:out>
                            <small><fmt:formatDate value="${cr.key.commenttime }" pattern="yyyy年MM月dd日 HH时mm分"/></small>
                        </h4>
                        ${cr.key.content }
                        <c:choose>
                          <c:when test="${cr.value != null }">
	                        <div class="media">
	                            <div class="media-body">
	                                <h4 class="media-heading"><c:out value="${cr.value.fromUser }"/> 回复 <c:out value="${cr.value.toUser }"/>
	                                    <small><fmt:formatDate value="${cr.value.replytime }" pattern="yyyy年MM月dd日 HH时mm分"/></small>
	                                </h4>
	                               	${cr.value.content }
	                            </div>
	                        </div>
                          </c:when>
                        </c:choose>
                    </div>
                </div>
                <hr>
				</c:forEach>
				  <div class="bdsharebuttonbox"><a href="#" class="bds_more" data-cmd="more"></a><a href="#" class="bds_qzone" data-cmd="qzone"></a><a href="#" class="bds_tsina" data-cmd="tsina"></a><a href="#" class="bds_tqq" data-cmd="tqq"></a><a href="#" class="bds_renren" data-cmd="renren"></a><a href="#" class="bds_weixin" data-cmd="weixin"></a></div>
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
  <script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
            </div>
            
            <div class="col-md-4">
			    <div class="well">
				<!-- Blog Search Well -->
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
				</div>
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
						<div class="col-lg-6">
							<ul class="list-unstyled">
								<c:forEach items="${requestScope.listSubCategory }"
									var="subCategory" begin="${requestScope.halfOfCates + 1}">
									<li><a href="${pageContext.request.contextPath }/passage/page/${subCategory.id}/1">${subCategory.name }</a>&nbsp;(${subCategory.passageCount })</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
				</div>
				
				<div class="well">
				  <h4>推荐网站</h4>
				  <h4></h4>
				  <c:forEach items="${listLink}" var="link">
				    <h4><a target="_blank" href="${link.url }"><c:out value="${link.name }"/></a></h4>
				  </c:forEach>
				</div>
				
				<div class="well">
					<h4>${build.content } : <fmt:formatDate value="${build.time }" pattern="yyyy年MM月dd日"/></h4>
					<h4>${lastUpdate.content } : <fmt:formatDate value="${lastUpdate.time }" pattern="yyyy年MM月dd日"/></h4>
				</div>
			</div>
		</div>
		<hr>

        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; WJY的笔记本</p>
                </div>
            </div>
        </footer>

    </div>

    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/wangEditor.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/passage.js"></script>
    
    <script type="text/javascript">
	  $(document).ready(function(){
		  $("#btn_submit").click(function(){
			  $("#search_form").submit();
		  });
	  });
	</script>
	<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1260575777'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1260575777%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));</script>
</body>

</html>
