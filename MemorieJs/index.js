//------------------------------------------Variables------------------------------------------//
//Mode
var vs = document.getElementById('vs');
var time = document.getElementById('time');
var ia = document.getElementById('ia');
var gameMode = 1;
var mainBoard; // initialise quand on lance une partie
var block = false; // bloque l'evenement tuile.setEvent
var gameStarted = false;

//Player
var player = "one";
var parameterName = document.getElementById('parameterName')
var Name = document.getElementById('nameOne');
var nameVs = document.getElementById('nameTwo');

//Bots
var difficulty = document.getElementById('difficulty');
var choiceDifficulty = 1;
var easy = document.getElementById('easy');
var medium = document.getElementById('medium');
var hard = document.getElementById('hard');

//Score
var score = document.getElementById('score');
var scoreOne = document.getElementById('scoreOne')
var scoreTwo = document.getElementById('scoreTwo')
var scoreTime;
var valueScore = 0;
var valueScoreOne = 0;
var valueScoreTwo = 0;

//Board
var boardDiv = document.getElementById('board');

//Timer
var parameterTime = document.getElementById("parameterTime");
var chrono = document.getElementById('chrono');
var timeValue;
var started = false;
var sec = 0;
var min = 0;

//Name
var parameterVS = document.getElementById("parameterVS");
//------------------------------------------Class Tile------------------------------------------//
function Tile() {
    this.id = 0;
    this.x = 0;
    this.y = 0;
    this.isReveal = false;
    this.isCompleted = false;
}

Tile.prototype.display = function () {
    var tile = document.getElementById(((this.x * 5) + this.y).toString());
    if (this.isReveal == false) {
        tile.children[0].src = "pictures/0.png";
    } else {
        tile.children[0].src = "pictures/" + this.id + ".png";
    }
}

Tile.prototype.flipTile = function () {
    if (this.isReveal == true) {
        this.isReveal = false;
    } else {
        this.isReveal = true;
    }
}

Tile.prototype.setProperties = function (newX, newY, newId) {
    this.x = newX;
    this.y = newY;
    this.id = newId;
}

Tile.prototype.setEvent = function () {
    // On recupère la div de la tuile correspondante
    // si la tuile n'est pas révélé , déja trouver avec son double, et que la partie n'est pas fini
    //  -> on retourne et affiche la carte et on regarde cb de carte sont retourné
    //      -> si 2 : on effectue la verification
    var timeClick;
    var divCurrent = document.getElementById(((this.x * 5) + this.y).toString()).firstElementChild;

    divCurrent.addEventListener("click", () => {
        divCurrent.parentElement.style.backgroundImage = "black";

        if ((block != "none") && (player != "bot")&& (this.isReveal != true) && (this.isCompleted != true) && (valueScore < 24)) {
            this.flipTile();
            this.display();

            if (block == false) {
                block = true;
                search();
            } else {
                timeClick = window.setTimeout(search, 1000);
                block = "none";
            }
        }
    });
}

//------------------------------------------Class Board------------------------------------------//
function IA() {
    this.cards = [];
}

IA.prototype.addCard = function(card) {
    var verif = false;
    for(var elt = 0; elt < this.cards.length; elt++){
        if(this.cards[elt] == card){
            verif = true;
        }
    }
    if(verif == false){
        if(choiceDifficulty == 1){
            let nb = Math.random();
            let nbTwo = Math.random();
            if (nb >= nbTwo) {
                this.cards.push(card);
            }
        }
        if(choiceDifficulty == 2){
            this.cards.push(card);
        }
    }
}

IA.prototype.search = function () {
    for (var x = 0; x < this.cards.length; x++) {
        for (var y = 0; y < this.cards.length; y++) {
            if((this.cards[y].isCompleted == false)&&(this.cards[x].isCompleted == false)) {
                if((this.cards[x].id == this.cards[y].id)&&(this.cards[x].x != this.cards[y].x)&&(this.cards[x].y != this.cards[y].y)){
                    return [this.cards[x], this.cards[y]];
                }
            }
        }
    return [];
    }
}   

//------------------------------------------Class Board------------------------------------------//
function Board() {
    this.tiles = [];
    this.reinit();
    this.ia = new IA();
}

Board.prototype.display = function () {
    // On génère 5 ranger de 5 cartes avec 2 class : card et ligne pour les placer directement en une grille de 5x5
    boardDiv.innerHTML = "";

    for (var i = 0; i < 5; i++) {
        var newLine = document.createElement("div");
        newLine.className = "line";

        for (var j = 0; j < 5; j++) {
            var newCard = document.createElement("div");
            var newImg = document.createElement("img");

            newCard.className = "card";
            newCard.id = (5 * i) + j;
            newCard.appendChild(newImg);
            newLine.appendChild(newCard);
        }
        boardDiv.appendChild(newLine);
    }
    for (var ind = 0; ind < this.tiles.length; ind++) {
        this.tiles[ind].setEvent(this);
        this.tiles[ind].display();
    }
}

Board.prototype.reinit = function () {
    this.tiles = [];
    var pos = 0;
    var tempo = 0;
    var idCard = [1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13];
    for (var i = 0; i < 10; i++) {
        for (var ind = 0; ind < idCard.length; ind++) {
            while (pos == ind) {
                pos = Math.floor(Math.random() * idCard.length);
            }
            tempo = idCard[pos];
            idCard[pos] = idCard[ind];
            idCard[ind] = tempo;
        }
    }
    for (var i = 0; i < 5; i++) {
        for (var j = 0; j < 5; j++) {
            var newTile = new Tile();
            newTile.setProperties(i, j, idCard[(i * 5) + j]);
            this.tiles.push(newTile);
        }
    }
    this.display();
}

Board.prototype.search = function() {
    var actifTiles = [];
    for (var ind = 0; ind < this.tiles.length; ind++) {
        if ((this.tiles[ind].isReveal == true) && (this.tiles[ind].isCompleted == false)) {
            actifTiles.push(this.tiles[ind]);
            if (gameMode == 2) {
                this.ia.addCard(this.tiles[ind]);
            }
        }
    }
    if (actifTiles.length == 2) {
        return this.validate(actifTiles);
    }
}

Board.prototype.validate = function (actifTiles) {
    if (actifTiles.length == 2) {
        if (actifTiles[0].id == actifTiles[1].id) {

            var firstTile = document.getElementById(((actifTiles[0].x * 5) + actifTiles[0].y).toString());
            var secondTile = document.getElementById(((actifTiles[1].x * 5) + actifTiles[1].y).toString());

            // bloque les tuiles
            actifTiles[0].isCompleted = true;
            actifTiles[1].isCompleted = true;

            if (gameMode == 1) {
                valueScoreOne += 2;
                valueScore += 2
                score.textContent = "Score : " + valueScore.toString();
                firstTile.children[0].style.borderColor = "blue";
                secondTile.children[0].style.borderColor = "blue";
            }
            if (gameMode != 1) {
                valueScore += 2;
                if (player != "one") {
                    valueScoreTwo += 2;
                    scoreTwo.textContent = nameVs.value.toString() + " : " + valueScoreTwo.toString();
                    firstTile.children[0].style.borderColor = "red";
                    secondTile.children[0].style.borderColor = "red";
                }
                else {
                    valueScoreOne += 2;
                    scoreOne.textContent = Name.value.toString() + " : " + valueScoreOne.toString();
                    firstTile.children[0].style.borderColor = "blue";
                    secondTile.children[0].style.borderColor = "blue";
                    block = false;
                }
            }

        } else {
            // retourne les cartes
            actifTiles[0].flipTile();
            actifTiles[1].flipTile();
            actifTiles[0].display();
            actifTiles[1].display();
            if (player != "one") {
                player = "one";
                if (gameMode == 0) {
                    scoreOne.style.backgroundColor = "white";
                    scoreTwo.style.backgroundColor = "black";
                }
            }
            else {
                if (gameMode == 0) {
                    scoreOne.style.backgroundColor = "black";
                    scoreTwo.style.backgroundColor = "white";
                    player = "two";
                }
                if (gameMode == 2 && valueScore < 24) {
                    block = "none";
                    this.iaPlay();
                }
            }
        }
    }
    if(gameMode != 2){
        block = false;}
    if ((valueScore == 24) || (min == 2)) {
        if(gameMode == 2){
            this.ia.cards = [];
            scoreGame();
        }
        if(gameMode == 1){
            window.clearTimeout(timeValue);
        }
        if(gameMode == 0){
            scoreGame();
        }
        return ;
    }
}

Board.prototype.iaPlay = function () {
    var list = this.ia.search();
    var tileOne = searchCard(this,tileOne);
    var tileTwo = searchCard(this,tileTwo);
    if(list.length == 2){
        tileOne = list[0];
        tileTwo = list[1];
    }else{
        while(tileOne == tileTwo){
            tileOne = searchCard(this,tileOne);
            tileTwo = searchCard(this,tileTwo);
        }
    }
    this.ia.addCard(tileOne);
    list = this.ia.search();
    if(list.length == 2){
        tileOne = list[0]
        tileTwo = list[1];
    }
    this.ia.addCard(tileTwo);
    var currentTwo = document.getElementById(((tileTwo.x *5) + tileTwo.y).toString());
    var currentOne = document.getElementById(((tileOne.x *5) + tileOne.y).toString())
    let time = setTimeout(function(){
        if((tileOne.isCompleted == false)&&(tileTwo.isCompleted == false)){
            tileTwo.flipTile();
            tileOne.flipTile();
            tileOne.display();
            tileTwo.display();
            if (tileOne.id == tileTwo.id){
                tileOne.isCompleted = true;
                tileTwo.isCompleted = true;
                currentOne.children[0].style.borderColor = "red";
                currentTwo.children[0].style.borderColor = "red";
                valueScoreTwo += 2;
                scoreTwo.textContent = nameVs.value.toString() + " : " +valueScoreTwo.toString();
                valueScore += 2;
                if(valueScore < 24){
                    let timeTrue = setTimeout(function(){
                        return iaPlay(mainBoard);
                    },1800)
                }
                return false;
            }
            else{
                let timeFalse = setTimeout(function(){
                    tileTwo.flipTile();
                    tileOne.flipTile();
                    tileTwo.display();
                    tileOne.display();
                },1800)
                let timeFalse2 = setTimeout(function(){
                    block = false;
                },2300)
                return false;
            }
        }
    },500)
}

function searchCard(board,tileNb){
    var nb = Math.floor(Math.random() * 20) + Math.floor(Math.random() * 5);
    while (board.tiles[nb].isCompleted == true) {
        nb = Math.floor(Math.random() * 20) + Math.floor(Math.random() * 5);
    }
    tileNb = board.tiles[nb];
    return tileNb;
}

function iaPlay(board){
    board.iaPlay();
}
//------------------------------------------Functions------------------------------------------//
//-------------------- Mode
function setGameMode(pos) {
    var elt = [vs, time, ia]
    if(gameStarted == false){
        for (var x = 0; x < 3; x++) {
            if (x != pos) {
                elt[x].className = "inactif";
            }
            if (x == pos) {
                gameMode = pos;
                elt[x].className = "actif";
            }
            setBoard();
        }
    }
}

function changeDifficulty() {
    let elt = [easy, medium, hard]
    for (var x = 0; x < 3; x++) {
        if (elt[x].checked == true) {
            choiceDifficulty = x;
        }
    }
}

function startGame() {
    scoreOne.textContent = Name.value.toString() + " : " + valueScoreOne.toString();
    resetBoard();
    player = "one";
    if (verifyNameIsValid(Name) == true) {
        if (gameMode == 0) {
            if (verifyNameIsValid(nameVs) == true) {
                mainBoard = new Board();
                gameStarted = true;
                choicePlayer();
                scoreTwo.textContent = nameVs.value.toString() + " : " + valueScoreTwo.toString();
                setBoard();
            }
        }
        mainBoard = new Board();
        if (gameMode == 1) {
            gameStarted = true;
            scoreTwo.textContent = nameVs.value.toString() + " : " + valueScoreTwo.toString();
            chronoGame();
            setBoard();
        }
        if (gameMode == 2) {
            gameStarted = true;
            choicePlayer();
            changeDifficulty();
            scoreTwo.textContent = nameVs.value.toString() + " : " + valueScoreTwo.toString();
            setBoard();
        }
    }
}

function choicePlayer() {
    player = "one";
    scoreOne.style.backgroundColor = "white";
    scoreTwo.style.backgroundColor = "black";
    if (gameMode == 2) {
        nameVs.value = "Bot";
        scoreTwo.style.backgroundColor = "white";
    }
    if (gameMode == 0) {
        var int = Math.round(Math.random());
        if (int == 0) {
            player = "two";
            scoreOne.style.backgroundColor = "black";
            scoreTwo.style.backgroundColor = "white";
        }
    }
}
//---------------------------------------- Game
//-------------------- Validate
function search() {
    mainBoard.search();
}
////-------------------- Display

function setBoard() {
    if (gameStarted == true) {
        difficulty.style.display = "none";
        parameterName.style.display = "none"
        boardDiv.style.display = "block";
        Name.style.display = "none";
        nameVs.style.display = "none";
        scoreOne.style.display = "block";
        scoreTwo.style.display = "block";
        if (gameMode != 1) {
            parameterTime.style.display = "none";
            parameterVS.style.display = "block";
        }
        if (gameMode == 1) {
            parameterTime.style.display = "block";
            parameterVS.style.display = "none";
        }
    }
    if (gameStarted == false) {
        difficulty.style.display = "none"
        parameterVS.style.display = "none";
        parameterName.style.display = "block";
        parameterTime.style.display = "none";
        scoreOne.style.display = "none";
        scoreTwo.style.display = "none";
        boardDiv.style.display = "none";
        Name.style.display = "block";
        nameVs.style.display = "none";
        if (gameMode == 0) {
            nameVs.style.display = "block";
            nameVs.value = "Player2";
        }
        if (gameMode != 0) {
            nameVs.style.display = "none";
            if (gameMode == 2) {
                difficulty.style.display = "block";
            }
        }
    }
}

function resetBoard() {
    if (gameStarted == true) {
        gameStarted = false;
        resetScore();
        setBoard();
    }
}

//-------------------- Time
function chronoGame() {
    sec = 0;
    min = 0;
    if (started == false) {
        timeValue = window.setTimeout(startTime, 1000);
        started = true;
    }
}

function startTime() {
    if ((valueScore == 24) || (min == 2)) {
        min = 0;
        sec = 0;
        scoreGame();
    } else {
        sec++;
        if (sec >= 60) {
            sec = 0;
            min++;
        }
        chrono.innerHTML = (min > 9 ? min : "0" + min) + ":" + (sec > 9 ? sec : "0" + sec);
        tempo = setTimeout(startTime, 1000);
    }

}
//-------------------- Score
function scoreGame() {

    if ((valueScore == 24 && gameMode == 1 ) || ((gameMode == 0 || gameMode == 2) && valueScoreOne > 12)) {
        alert("vous avez gagnez");
    }
    else if ((gameMode == 1 || gameMode == 2) && valueScoreOne == 12) {
        alert("vous avez fait exqueo");
    }
    else {
        if (gameMode == 1) {
            alert("vous avez perdue");
        }
        if (gameMode == 2) {
            alert("Le bot à gagné");
        }
        if (gameMode == 0) {
            alert(nameVs.value.toString() + " à gagné");
        }

    }
    resetBoard();
}

function resetScore() {
    score.value = 0;
    score.textContent = "score : 0";
    scoreOne.value = 0;
    scoreOne.textContent = Name.value.toString() + " : " + 0;
    scoreTwo.value = 0;
    scoreTwo.textContent = nameVs.value.toString() + " : " + 0;
}


//-------------------- Names
function eltIsNumber(elt) {
    if ((elt >= '0') && (elt <= '9')) { return 1; }
    return 0;
}

function eltIsLetter(elt) {
    if (((elt >= 'a') && (elt <= 'z')) || ((elt >= 'A') && (elt <= 'Z'))) { return 1; }
    else { return 0; }
}

function nameIsPseudo(pseudo) {
    if (pseudo.length == 0) {
        return 0
    }
    for (var ind = 0; ind < pseudo.length; ind++) {
        if ((eltIsLetter(pseudo[ind]) == 0) && (eltIsNumber(pseudo[ind]) == 0)) {
            return 0;
        }
    }
    return 1;
}

function verifyNameIsValid(pseudo) {
    if (nameIsPseudo(pseudo.value) == 1) {
        pseudo.style.borderColor = "black";
        pseudo.style.borderWidth = "1px";
        return true;
    }
    else {
        pseudo.style.borderColor = "red"
        pseudo.style.borderWidth = "2px";
        return false;
    }
}

//-------------------- Main --------------------//
function main() {
    setBoard();
}

window.onload = main;