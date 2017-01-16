/**
 *
 * Created by Dejv on 12.01.17.
 */

var socket = new WebSocket("ws://mysupperapp-javaeeschool2.44fs.preview.openshiftapps.com/jboss-helloworld/chat");
var ACTION_ADD = "add";
var ACTION_SET_USERNAME = "username";

socket.onmessage = onMessage;

function onMessage(event) {
    var message = JSON.parse(event.data);
    if (message.action === "add") {
        printMessage(message);
    }
}

function addMessage(body) {
    var messageAddAction = {
        action: ACTION_ADD,
        body: body
    };
    socket.send(JSON.stringify(messageAddAction));
}


function printMessage(message) {
    var content = document.getElementById("content");

    var messageDiv = document.createElement("div");
    content.appendChild(messageDiv);

    var messageDate = document.createElement("span");
    messageDate.style.display = "block";
    messageDate.style.fontSize = "10px";
    messageDate.innerHTML = message.created_at+" ";
    messageDiv.appendChild(messageDate);


    var messageUser = document.createElement("span");
    messageUser.style.display = "block";
    messageUser.style.fontWeight = "bold";
    messageUser.innerHTML = message.username+": ";
    messageDiv.appendChild(messageUser);

    var messageBody = document.createElement("p");
    messageBody.innerHTML = message.message;
    messageDiv.appendChild(messageBody);

    content.scrollTop = content.scrollHeight;
}


function setUsername(username){
    var messageUsernameAction = {
        action: ACTION_SET_USERNAME,
        username: username
    };
    socket.send(JSON.stringify(messageUsernameAction));
}

function formSubmitUsername() {
    var form = document.getElementById("username-form");
    var username = form.elements["username"].value;
    document.getElementById("username-form-wrapper").style.display = "none";
    document.getElementById("chat-form-wrapper").style.display = "block";

    setUsername(username);
    return false;
}

function formSubmitMessage() {
    var form = document.getElementById("chat-form");
    var message = form.elements["message"].value;
    addMessage(message);
    form.elements["message"].value = "";
    return false;
}