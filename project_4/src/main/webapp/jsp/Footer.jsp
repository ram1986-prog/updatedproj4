<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Result System</title>
</head>
<body>

<style>

.footer {
  position:fixed;
  left: 0;
  bottom: 0;
  width: 100%;
  height:8%;
  background-color: white;
  color: darkblack;
  text-align: center;
}
.blink {
        animation: blinker 10s linear infinite;
        color: grey;
        font-size: 15px;
        font-weight: bold;
        font-family: timesnewroman;
      }
      @keyframes blinker {
        50% {
          opacity: -40;
        }
      } 
 
</style>

<div class="footer">

<hr class="blink">
	
	<H3><a></a>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&copy RAYS Technologies</a>
	
	<a><font  class="blink" >&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<%= (new java.util.Date()).toLocaleString()%></font></a></H3>
 	
 	
 </div>
</body>
</html>