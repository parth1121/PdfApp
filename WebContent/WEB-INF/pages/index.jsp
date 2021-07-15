 <!DOCTYPE html>
    <html lang="en">
    <head>
      <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

      <title>Advance Java</title>
      <link rel="stylesheet" href="/feature.css">
    </head>
    <body>
    	<div class="topnav">
		  <a  href="#home">MERGE PDF</a>
	  	  <a href="#news">SPLIT PDF</a>
	  	  <a href="xyz.jsp">COMPRESS PDF</a>
	  	<div class="dropdown">
	  		<button class="dropbtn" onclick="myFunction()"> CONVERT TO PDF<i class="fa fa-caret-down"></i>
	  		</button>
	  		<div class="dropdown-content" id="myDropdown">
	  		
                            <a  href="#">JPG to PDF</a>
                            
            </div>
        </div>
                    <div class="dropdown">
			  		<button class="dropbtn" onclick="myFunction1()"> CONVERT FROM PDF<i class="fa fa-caret-down"></i>
	  				</button>
	  				<div class="dropdown-content" id="myDropdownfrompdf">
                            <a  href="#"> PDF to JPG</a>
                      </div>
    				</div>
  			<script>
function myFunction() {
  document.getElementById("myDropdown").classList.toggle("show");
}
window.onclick = function(e) {
  if (!e.target.matches('.dropbtn')) {
  var myDropdown = document.getElementById("myDropdown");
    if (myDropdown.classList.contains('show')) {
      myDropdown.classList.remove('show');
    }
  }
}

function myFunction1() {
  document.getElementById("myDropdownfrompdf").classList.toggle("show");
}
window.onclick = function(e1) {
  if (!e1.target.matches('.dropbtn')) {
  var myDropdown1 = document.getElementById("myDropdownfrompdf");
    if (myDropdown1.classList.contains('show')) {
      myDropdown1.classList.remove('show');
    }
  }
}

// Get the elements with class="column"
var elements = document.getElementsByClassName("column");

// Declare a loop variable
var i;

// Grid View
function gridView() {
  for (i = 0; i < elements.length; i++) {
    elements[i].style.width = "50%";
  }
}




</script>
	  	
		<a href="#about">ALL PDF TOOLS</a>
		<a style="float: right" href="#login" >LOG IN</a>
		<a class="active" style="float: right" href="#login" >SIGN IN</a>
		
		</div>

    <center>
      <header>
      	<h1 style="padding-top: 10px;">Every tool you need to work with PDFs in one place!</h1>
        <br>
        <p style="font-size:20px ;padding-top: 10px;padding-bottom: 10px">Every tool you need to use PDFs, at your fingertips. All are 100% FREE and easy to use! Merge,
        </p>
        <p style="font-size:30px"> split, compress, convert, rotate, unlock and watermark PDFs with just a few clicks.</p>
      </header>
      </center>
       
           <article>
            <section>
            
      <div class="grid-container" style="background-color:#aaa;">
      <div class="Compress">
      
      <h2>Merge PDF</h2>
      <br>
       <p>Combine PDFs in the order you want with the easiest PDF merger available.</p>
                </div>
                </div>
      <div class="grid-container" style="background-color:#bbb;">
      <div class="Compress">
      
      <h2>Split PDF</h2>
      <br>
          <p>Separate one page or a whole set for easy conversion into independent PDF files.</p>
               </div>
               </div>


      <div class="grid-container" style="background-color:#aaa;">
      <div class="Compress">
      <h2>Compress PDF</h2>
      <br>
        <p>Reduce file size while optimizing for maximal PDF quality.</p>
                        </div>
                        </div>          
      
   
      </section>
         </article>

        <center><footer>Thankyou</footer></center>

    </body>
    </html>