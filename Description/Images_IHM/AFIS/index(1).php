.drop-shadow{position:relative;}
.drop-shadow:before,.drop-shadow:after{
  content:"";
  position:absolute;
  z-index:-1;
  width:50%;
  height:30px;
  max-width:300px;
  bottom:10px;
  box-shadow:0 10px 10px rgba(0,0,0,0.5);
}
.drop-shadow:before{
  left:10px;
  -webkit-transform:rotate(-3deg);    
  -moz-transform:rotate(-3deg);   
  -ms-transform:rotate(-3deg);   
  -o-transform:rotate(-3deg);
  transform:rotate(-3deg);
}
.drop-shadow:after{
  right:10px; 
  -webkit-transform:rotate(3deg);   
  -moz-transform:rotate(3deg);  
  -ms-transform:rotate(3deg);  
  -o-transform:rotate(3deg);
  transform:rotate(3deg);
}
/*  styles for the unit rater     */
.ratingblock {
/*	width: 50%;*/
	display:block;
	padding-bottom:8px;
	margin-bottom:8px;
	}

.loading {
	height: 30px;
	background: url('images/rating/working.gif') 50% 50% no-repeat;
	}
	
.unit-rating { /* the UL */
	list-style:none;
	margin: 0px;
	padding:0px;
	height: 30px;
	position: relative;
	overflow:hidden;
	background: url('images/rating/starrating.gif') top left repeat-x;		
	}

.unit-rating li{
    text-indent: -90000px;
	height: 30px;
	padding:0px;
	margin:0px;
	display: block;
	float: left;
	}
	
.unit-rating li a {
  border:0px;
	outline: none;
	display:block;
	position: absolute;
	width:30px;
	height: 30px;
	text-decoration: none;
/*	text-indent: -9000px;*/
	z-index: 10;
	padding: 0px;
	}
	
.unit-rating li a:hover{
	background: url('images/rating/starrating.gif') left center;
	z-index: 2;
/*	left: 0px;*/
	}

.unit-rating li.current-rating {
	position: absolute;
	background: url('images/rating/starrating.gif') left bottom;
	/*z-index: 1;*/
	}

.unit-rating li.no-rating {
	position: absolute;
	background: url('images/rating/starrating.gif')  top left repeat-x;
	z-index: 20;
	}

.thanks {color:rgb(148, 44, 30);}
.pale {bottom:100%;background:rgba(255,255,255,0.75);box-shadow: 0 0 8px #ccc;}