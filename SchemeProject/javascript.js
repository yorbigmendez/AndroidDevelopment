
//We are going to increment by or decrease by 1 the offset
var step = 1;
var my_time;

//Timer used to move left
function timerL(){
    disp("L");
}

//Have a timer to move right
function timerR(){
    disp("R");
}

//Used to edit the style left of the object;
function disp(whereToNext){
    if(whereToNext == "L"){//Move boat Left
        var x=document.getElementById('boatContent').offsetLeft;
        //alert("Hello");
        if(x > 0){
            x= x - step;
            console.log("My boat going left value: " +x);
            document.getElementById('boatContent').style.left= x + "px"; 
            //document.getElementById('boatContent0').offsetLeft = x;
            // horizontal movment
        }else{
             clear();
        }
    //Move to the right
    }else{
        var x=document.getElementById('boatContent').offsetLeft;
        if(x < 650){
            x= x +step;
           
            console.log("My boat right value: " +x);
            document.getElementById('boatContent').style.left= x + "px"; 
            //document.getElementById('boatContent0').offsetLeft = x;
            // horizontal movment
        }
        else{
             clear();
        }
    }  
}

function loadBoat(m,c){
    //Set noting into the misionaries and cannibals     
    document.getElementById('misionaries').innerHTML = "";
    document.getElementById('cannibals').innerHTML = "";
    for(var x=0; x<m ;x++){
        $('#misionaries').append('<img src="misionero.jpg" />'); 
    }
    for(var y=0; y<c ;y++){
        $('#cannibals').append('<img src="canibal.png" />'); 
    }
}

function loadSide(m,c,side){
    var content;
    var contM = 0;
    var contC = 0;
    if(side == "L"){//Have to load the left side
        content = document.getElementById('leftContent');
        content.innerHTML = "";
        for(contM;contM<m;contM++){
            $('#leftContent').append('<img src="misionero.jpg" />'); 
        }

        for(contC; contC<c ; contC++){
            $('#leftContent').append('<img src="canibal.png" />');
        }
    }else{//Have to load the right side
        content = document.getElementById('rightContent');
        content.innerHTML = "";
        for(contM;contM<m;contM++){
            $('#rightContent').append('<img src="misionero.jpg" />'); 
        }

        for(contC; contC<c ; contC++){
            $('#rightContent').append('<img src="canibal.png" />');
        }
    }
}

var answerTxt;//Going to save the answer from txt file
var arrayMovements = [];
var i = -1;

//loadBoat(1,1);
    //Start action

//timerL();
function displayMovement(){
    ++i;
    //loadSide(2,2,"L");
    //Load boat content
    
        //loadBoat(0,0);
        console.log("Movement "+arrayMovements[i])
        //loadSides
        loadSide(arrayMovements[i].canibalsRight,arrayMovements[i].misionariesRight, "R");
        loadSide(arrayMovements[i].canibalsLeft,arrayMovements[i].misionariesLeft,"L");
        //Load boat for movement
        if(i < arrayMovements.length){//It is not the last state
            console.log("Boat state here: " + arrayMovements[i].boatState);
            loadBoatForMovement(i,arrayMovements[i].boatState);
        }
        if(i!=0){//Not first state
            console.log("Going to validate the state="+arrayMovements[i].boatState);
            if(arrayMovements[i].boatState == "L"){
                console.log("Going to move left");
                document.getElementById('boatContent').style.left = 650;
                my_time = window.setTimeout('timerR()',20);
            }else{
                console.log("Going to move right");
                document.getElementById('boatContent').style.left = 0;
                //Moving RIght
                my_time = window.setTimeout('timerL()',20);
            }
        }
        
}

 function stop() {
    if (my_time) {
        window.clearTimeout(my_time)
        timer = 0;
    }
 }

var x = 0;
function loadBoatForMovement(i,boatState){
    var cannibals = 0;
    var misionaries = 0;
    ++i;
    if(boatState == "L"){//Moving to the Right
        if(arrayMovements[x+1].canibalsRight > arrayMovements[x].canibalsRight){//Se paso un canibal
            cannibals =  arrayMovements[x+1].canibalsRight - arrayMovements[x].canibalsRight;
        }else if(arrayMovements[x+1].misionariesRight > arrayMovements[x].misionariesRight){
            misionaries =  arrayMovements[x+1].misionariesRight - arrayMovements[x].misionariesRight;
        }   
    }else{//Moving to the left
        if(arrayMovements[x+1].canibalsLeft > arrayMovements[x].canibalsLeft){//Se paso un canibal
            cannibals =  arrayMovements[x+1].canibalsLeft - arrayMovements[x].canibalsLeft;
        }else if(arrayMovements[x+1].misionariesLeft > arrayMovements[x].misionariesLeft){
            misionaries =  arrayMovements[x+1].misionariesLeft - arrayMovements[x].misionariesLeft;
        }   
    }
    console.log("Misionaries moving: "+misionaries + "Cannibals moving: "+cannibals);
    loadBoat(cannibals,misionaries);
    
}


function processFile(){
    answerTxt = answerTxt.slice(1);//Eliminate the first bracket
    answerTxt = answerTxt.substring(0, answerTxt.length - 1);//Eliminate the last brackets
    answerTxt = reverse(answerTxt);//Sets the answer in the correct flow
    var res = answerTxt.split("]");
    arrayMovements = [];//Empty array
    for(var i in res){
        if(res[i].charAt(1) != ""){
            //Creates new movement
            var m = new Movement(res[i].charAt(5),res[i].charAt(8),res[i].charAt(11),res[i].charAt(14));
            if(res[i].charAt(1) == "D"){//Is right
                m.boatState = "R";
            }else{//Is left
                m.boatState = "L";
            }
            arrayMovements.push(m);//Inserts that movement
        }
    }
}

function reverse(s){
    return s.split("").reverse().join("");
}


function loadArchive(){
	if (window.XMLHttpRequest) {
    	xhttp = new XMLHttpRequest();
    } else {
	    // code for IE6, IE5
	    xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    
    xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {
	    	answerTxt = (xhttp.responseText);
            console.log('file answer' + answerTxt);
            processFile();
	    }
	};
	xhttp.open("GET", "gameAnswer.txt", true);
	xhttp.send();
} 

loadArchive();

//Used for the movement states
function Movement (mR,cR,mL,cL) {
    this.misionariesLeft = mL;
    this.misionariesRight = mR;
    this.canibalsLeft = cL;
    this.canibalsRight = cR;
}