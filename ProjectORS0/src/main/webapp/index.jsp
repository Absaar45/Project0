<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html lang="en">

<head>
  <title>ORS</title>
  <!-- Required meta tags -->
  <meta charset="utf-8">
 <link rel="icon" type="image/png" href='<c:url value="http://localhost:8080/ProjectORS0/resources/img/favicon.ico"></c:url>'>
	<link rel="apple-touch-icon" sizes="76x76" href="http://localhost:8080/ProjectORS0/resources/img/favicon.ico">
  <meta content="width=device-width, initial-scale=1.0" name="viewport" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <!--     Fonts and icons     -->
   <link rel="stylesheet" type="text/css" href='<c:url value="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons"></c:url>'/>
  <link rel="stylesheet" href='<c:url value="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"></c:url>'>
  <!-- Material Kit CSS -->
  <link href="<c:url value="http://localhost:8080/ProjectORS0/resources/css/material-kit.css?v=2.0.7"></c:url>" rel="stylesheet" />
  
  
 
</head>

<body>
  

<!-- ORS WELCOME STATR -->
   
  <div class="page-header header-filter" data-parallax="true" style="background-image: url('resources/img/bg3.jpg')">
    <div class="container">
      <div class="row">
        <div class="col-md-8 ml-auto mr-auto">
           <div class="brand text-center">
            <div class="content-center" style="margin-top: 0px !important;">
               <img src="resources\img\customLogoTL.png" alt="Rounded Image" class="rounded img-fluid">
              <h1 class="presentation-subtitle text-center"><a class="btn btn-lg btn-success text-white" href='<c:url value="/Welcome"></c:url>'>Online Result system</a></h1>
            </div>
          
          </div>
        </div>
      </div>
    </div>
  </div>
<!-- ORS WELCOME END -->
   
      <!--   Core JS Files   -->
 <script src="http://localhost:8080/ProjectORS0/resources/js/core/jquery.min.js" type="text/javascript"></script>
  <script src="http://localhost:8080/ProjectORS0/resources/js/core/popper.min.js" type="text/javascript"></script>
  <script src='<c:url value="http://localhost:8080/ProjectORS0/resources/js/core/bootstrap-material-design.min.js"></c:url>' type="text/javascript"></script>
  <script src='<c:url value="http://localhost:8080/ProjectORS0/resources/js/plugins/moment.min.js"></c:url>'></script>
  <!--	Plugin for the Datepicker, full documentation here: https://github.com/Eonasdan/bootstrap-datetimepicker -->
  <script src='<c:url value="http://localhost:8080/ProjectORS0/resources/js/plugins/bootstrap-datetimepicker.js"></c:url>' type="text/javascript"></script>
  <!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
  <script src='<c:url value="http://localhost:8080/ProjectORS0/resources/js/plugins/nouislider.min.js"></c:url>' type="text/javascript"></script>
  <!--  Google Maps Plugin    -->
  <!-- Control Center for Material Kit: parallax effects, scripts for the example pages etc -->
  <script src='<c:url value="http://localhost:8080/ProjectORS0/resources/js/material-kit.js?v=2.0.7"></c:url>' type="text/javascript"></script>
  <script>
    $(document).ready(function() {
      //init DateTimePickers
      materialKit.initFormExtendedDatetimepickers();

      // Sliders Init
      /* materialKit.initSliders(); */
    });


 
  </script> 
        
</body>

</html>