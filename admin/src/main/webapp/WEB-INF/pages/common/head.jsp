<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> --%>

<!-- CSRF TOKEN -->
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<meta name="_csrf.parameterName" content="${_csrf.parameterName }"/>
<meta name="contextPath" content="${pageContext.servletContext.contextPath }"/>

<!-- Required Stylesheets -->
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/reset.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/text.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/fonts/ptsans/stylesheet.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/fluid.css" media="screen" />

<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/mws.style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/icons/icons.css" media="screen" />

<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/plugins/colorpicker/colorpicker.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/plugins/jimgareaselect/css/imgareaselect-default.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/plugins/fullcalendar/fullcalendar.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/plugins/fullcalendar/fullcalendar.print.css" media="print" />
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/plugins/tipsy/tipsy.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/plugins/sourcerer/Sourcerer-1.2.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/plugins/jgrowl/jquery.jgrowl.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/plugins/spinner/spinner.css" media="screen" />
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/jui/jquery.ui.css" media="screen" />

<!-- Theme Stylesheet -->
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/mws.theme.css" media="screen" />
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/jquery.datetimepicker.css" media="screen" /> --%>
<!-- JavaScript Plugins -->

<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/script/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/highcharts.js"></script>

<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/plugins/jimgareaselect/jquery.imgareaselect.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/plugins/jquery.dualListBox-1.3.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/plugins/jgrowl/jquery.jgrowl.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/plugins/jquery.filestyle.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/plugins/fullcalendar/fullcalendar.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/plugins/jquery.dataTables.js"></script>
<!--[if lt IE 9]>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/plugins/flot/excanvas.min.js"></script>
<![endif]-->
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/plugins/flot/jquery.flot.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/plugins/flot/jquery.flot.pie.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/plugins/flot/jquery.flot.stack.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/plugins/flot/jquery.flot.resize.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/plugins/colorpicker/colorpicker.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/plugins/tipsy/jquery.tipsy.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/plugins/sourcerer/Sourcerer-1.2.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/plugins/jquery.placeholder.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/plugins/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/plugins/jquery.mousewheel.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/plugins/spinner/ui.spinner.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/jquery-ui.js"></script>

<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/mws.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/themer.js"></script>

<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/main.js"></script>


<%-- <script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/jquery.datetimepicker.js"></script> --%>

