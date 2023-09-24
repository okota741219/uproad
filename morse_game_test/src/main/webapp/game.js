//let Q = ["apple","banana","melon","mango","starwberry","blueberry","orange"];//問題文


//let Q_morse = ["01 0110 0110 0100 0","1000 01 10 01 10 01","11 0 0100 111 10","11 01 10 110 111","000 1 01 010 011 1000 0 010 010 1011","1000 0100 001 0 1000 0 010 010 1011","111 010 01 10 110 0"]
//let Q_morse = ["・ー ・ーー・ ・ーー・ ・ー・・ ・","ー・・・ ・ー ー・ ・ー ー・ ・ー","ーー ・ ・ー・・ ーーー ー・","ーー ・ー ー・ ーー・ ーーー","・・・ ー ・ー ・ー・ ・ーー ー・・・ ・ ・ー・ ・ー・ ー・ーー","ー・・・ ・ー・・ ・・ー ・ ー・・・ ・ ・ー・ ・ー・ ー・ーー","ーーー ・ー・ ・ー ー・ ーー・ ・"];
//let Q_morse_naibu = ["010110011001000","10000110011001","110010011110","110110110111","0001010100111000 00100101011","10000100001 0100000100101011","11101001101100"]



let epsilon = 0.25; //トンツー判定の閾値

let input_beffer = new Array();
let input_is_completed = true;
let i = 0; //counter
let problem_element = new Array();
let is_first_push = true;

let when_the_game_start = 0; // ゲームを開始してたった時間
let limit_time = 25 // 秒

let num_words = 0// 問題数

let endgame=false; //ゲームの終了を判定

let mute = false;
let intervalID_stop;

let counter_bgm = 0;
let button_count = 0;

const game_bgm = new Audio('audio/game_bgm.mp3');
game_bgm.loop = true;

let is_available = false;

let accuracy = 0; // 正答率
let correct_answer = 0; // 正答数(文字数)
let incorrect_answer = 0; //　誤答数(文字数)
let correct_answer_prblm = 0; // 正答数(問題数)

let yuukousuuzi = 2;

let interval;

let permission = false // ゲームを開始していいかの判定


//カウントの秒数
let count = 3;

let Q = [];
let Q_morse = [];
let Q_No=0;
let Q_i = 0;//回答初期値・現在単語どこまで合っているか判定している文字番号
let Q_l = 0//計算用の文字の長さ
let mozisuu_l = 0

// 難易度から問題数を取得
function get_difficulty() {
    let difficulty = document.getElementById("difficulty-set").value;
    console.log(difficulty);
    if (difficulty === "easy"){
        num_words = 4;
    }else if (difficulty === "normal"){
        num_words = 6;
    }else if (difficulty === "hard"){
        num_words = 10;
    }
}

// 特定の場所から問題文を取得する。
function get_words() {
    get_difficulty();
    string_words = document.getElementById("get_words").innerText;
    console.log(string_words);
    words = string_words.split(","); // 偶数番目が問題文(アルファベット)、奇数番目が問題文(モールス信号)
    console.log(words);
    let len = words.length;
    for (let i = 0; i < len; i++){
        if (i%2 == 0){
            Q.push(words[i]);
        }else{
            Q_morse.push(words[i]);
        }
    }
    Q_l = Q_morse[Q_No].length;
    mozisuu_l = Q[Q_No].length;
    document.getElementById("start").innerHTML = Q[Q_No].substring(Q_i, Q_l); //問題を書き出す
    document.getElementById("morse").innerHTML = Q_morse[Q_No].substring(Q_i, Q_l);
}

// プログレスバーの進捗値
let val=0;
let intervalID_game;
window.addEventListener('DOMContentLoaded', function() {
    let element = document.getElementById('gameprogress');
    if (element) {
        element.style.visibility = "hidden";
    }
});

// プログレスバーの初期化
function init_progress() {
    val = 0;
    document.getElementById("gameprogress").value = val;
    document.getElementById("gameprogress").innerHTML = val + "%";
    accuracy = 0; // 正答率
    correct_answer = 0; // 正答数(文字数)
    incorrect_answer = 0; //　誤答数(文字数)
    correct_answer_prblm = 0; // 正答数(問題数)
}
// プログレスバーを更新する
function updateProgress() {
    // プログレスバーの進捗値を更新し、プログレスバーに反映させる
    let rate_change = 100 / num_words;
    val += rate_change;
    document.getElementById("gameprogress").value = val;
    document.getElementById("gameprogress").innerHTML = val + "%";
    console.log("progress:", val, "%");

    // 最大値まで達したら終了
    if (val == 100) {
        clearInterval(intervalID_game);
    }
}

window.document.onkeydown=function(event) {
    if (permission){
        if(is_first_push === false && !is_available){
            if (input_is_completed){
                if (event.keyCode === 32 && endgame === false){
                    input_beffer[i] = Date.now(); // 開始時間
                    input_is_completed = false;
                }
            }
        }else{
            when_the_game_start = Date.now();
            document.getElementById("gameprogress").style.visibility = "visible";
        }
    }
}

window.document.onkeyup=function(event) {
    if (permission){
        if(is_first_push === false && !is_available){
            if (event.keyCode === 32 && endgame === false){
                input_beffer[i] = Date.now() - input_beffer[i]; // 終了時間
                input_is_completed = true;
                game();
            }  
        }else{
            is_first_push = false;
        }  
    }
}

// startボタンを押したら呼ばれる関数
function permission_game(){
    //1000ミリ秒（1秒）ごとに、countDown関数を実行
    countDownInterval = setInterval(countDown,1000);
}

function permisson_game_act(){
    get_words();
    permission = true;
    document.getElementById("start_btn").style.visibility = "hidden";
    document.getElementById("restart").style.visibility = "hidden";
    document.getElementById("output").style.visibility = "hidden";
    document.getElementById("start").innerHTML = Q[Q_No].substring(Q_i, Q_l); //問題を書き出す
    document.getElementById("morse").innerHTML = Q_morse[Q_No].substring(Q_i, Q_l);
    document.getElementById("gameprogress").style.visibility = "visible";
    document.getElementById("morse").style.visibility = "visible";
    game_bgm.currentTime = 0;
    if(!mute) {
        game_bgm.play();
    }
    when_the_game_start = Date.now();
    init_progress();
    is_first_push = false;
}

//一定時間おきに行いたい関数を宣言
function countDown() {
    //タイマー表示要素を取得
    const timer = document.getElementById("start");
    document.getElementById("start_btn").style.visibility = "hidden";
    document.getElementById("start").style.visibility = "visible";
    if(count > 0) {
        //countが0より大きい場合はcountを1ずつ減らす
        count--;
        //タイマー表示要素にcountの数値を表示
        timer.textContent = count;
    } else {
        console.log("タイマーが停止しました");
        permisson_game_act();
        count = 3;
        clearInterval(countDownInterval);
    }
}



let stop_func = function(){
    endgame = true;
    if (correct_answer + incorrect_answer === 0){
        accuracy = 0;
    }else{
        accuracy = (correct_answer / (correct_answer + incorrect_answer)) * 100;
        accuracy = Math.floor(accuracy * Math.pow( 10, yuukousuuzi) ) / Math.pow( 10, yuukousuuzi);
    }
    window.document.getElementById("start").innerHTML = "規定された時間が経ちました";
    window.document.getElementById("morse").innerHTML = "正確性: " + accuracy + "%";
    window.document.getElementById("output").innerHTML = "経過時間: " + (Date.now() - when_the_game_start) / 1000 + "秒";
    val = 0;
    Q_i = 0;
    Q_l = 0;
    Q_No = 0;
    // bgmを停止
    game_bgm.pause();
    button();
    document.getElementById("gameprogress").style.visibility = "hidden";
    document.getElementById("restart").style.visibility = "visible";
    document.getElementById("output").style.visibility = "visible";
    clearInterval(intervalID_stop);
    clearInterval(intervalID_game);
}



function trans_signal(){
    interval = input_beffer[i]/1000;
    console.log(interval);
    if (epsilon <= interval){
        return "ー"; //ツー
    }else{
        return "・"; //トン
    }
}


// 正解時に音を出す
function audio() {
    if (!mute){
        const correct_sound = new Audio('audio/correct_sound.mp3');
        correct_sound.currentTime = 0;
        correct_sound.play();
    }
}
// 正解時の音をミュートする
function is_mute() {
    if (mute){
        mute = false;
        document.getElementById("speaker_mute").src = "image/speaker.png";
        if (permission){
            game_bgm.play();
        }
    }else {
        mute = true;
        document.getElementById("speaker_mute").src = "image/speaker_off.png";
        game_bgm.pause();
    }
}



// ゲーム内BGM
function game_audio() {
    if (mute){
        game_bgm.pause();
    }else{
        game_bgm.play();
    }
}

function init_val() {
    is_first_push = true;
    endgame = false;
    Q_No=0;
}

// 「もう一度」ボタンを作成
function button(){
    if (button_count == 0){
        // 新しいボタン要素を作成
        let btn = document.createElement("button");
        let target = document.getElementById("restart");
        // ボタンのテキストを設定
        btn.innerHTML = "もう一度";
        btn.type = "button";
        target.appendChild(btn);
        btn.onclick = () => {
            get_difficulty();
            document.getElementById("restart").style.visibility = "hidden";
            document.getElementById("morse").style.visibility = "hidden";
            document.getElementById("output").style.visibility = "hidden";
            document.getElementById("start").style.visibility = "hidden";
            permission_game();
            init_val();
        };
        button_count = 1;
    }
}

let mozisuu = 0;

function game(){
    if (counter_bgm === 0){
        game_audio();
        counter_bgm = 1;
    }
    if (input_is_completed){
        if (Q_l == Q_l-Q_i){
            document.getElementById("start").innerHTML = Q[Q_No].substring(Q_i, Q_l); //問題を書き出す
            document.getElementById("morse").innerHTML = Q_morse[Q_No].substring(Q_i, Q_l);
            mozisuu = 0;
            mozisuu_l = Q[Q_No].length;
            }
        console.log(trans_signal());
        if (Q_morse[Q_No].charAt(Q_i) === trans_signal()) { //押したキーが合っていたら
            console.log("correct");
            correct_answer++;
            i++;
            Q_i++; //判定する文章に１足す
            if (Q_morse[Q_No].substring(Q_i, Q_i+1) == ' '){
                Q_i++;
                mozisuu++;
            }
            document.getElementById("morse").innerHTML = Q_morse[Q_No].substring(Q_i, Q_l);
            document.getElementById("start").textContent = Q[Q_No].substring(mozisuu,mozisuu_l);

            if (Q_l-Q_i === 0){ //全部正解したら
                correct_answer_prblm += 1;
                updateProgress();
                audio();
                Q_No++; //次の問題を指定
                if (Q_No >= num_words){ // 終わる
                    stop_func();
                }else{
                    Q_i = 0;//回答初期値・現在どこまで合っているか判定している文字番号
                    Q_l = Q_morse[Q_No].length;//計算用の文字の長さ
                    mozisuu = 0;
                    mozisuu_l = Q[Q_No].length;
                    window.document.getElementById("start").innerHTML = Q[Q_No];
                    window.document.getElementById("morse").innerHTML = Q_morse[Q_No];
                }
            }
        }else{
            incorrect_answer++;
        }
    }
    
}