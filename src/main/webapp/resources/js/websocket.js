/**
 * Created by Dejv on 12.01.17.
 */
window.onload = init;
var socket = new WebSocket("ws://http://mysupperapp-javaeeschool2.44fs.preview.openshiftapps.com/jboss-helloworld/chat");
var ACTION_ADD = "add";
var ACTION_SET_USERNAME = "username";

socket.onmessage = onMessage;

function onMessage(event) {
    var message = JSON.parse(event.data);
    console.log("accepted message");
    console.log(message);
    if (message.action === "add") {
        printMessage(message);
    }
}

function printMessage(message) {
    var content = document.getElementById("content");

    var messageDiv = document.createElement("div");
    content.appendChild(messageDiv);

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
function addMessage(body) {
    var messageAddAction = {
        action: ACTION_ADD,
        body: body
    };
    socket.send(JSON.stringify(messageAddAction));

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


function printDeviceElement(device) {
    var content = document.getElementById("content");

    var deviceDiv = document.createElement("div");
    deviceDiv.setAttribute("id", device.id);
    deviceDiv.setAttribute("class", "device " + device.type);
    content.appendChild(deviceDiv);

    var deviceName = document.createElement("span");
    deviceName.setAttribute("class", "deviceName");
    deviceName.innerHTML = device.name;
    deviceDiv.appendChild(deviceName);

    var deviceType = document.createElement("span");
    deviceType.innerHTML = "<b>Type:</b> " + device.type;
    deviceDiv.appendChild(deviceType);

    var deviceStatus = document.createElement("span");
    if (device.status === "On") {
        deviceStatus.innerHTML = "<b>Status:</b> " + device.status + " (<a href=\"#\" OnClick=toggleDevice(" + device.id + ")>Turn off</a>)";
    } else if (device.status === "Off") {
        deviceStatus.innerHTML = "<b>Status:</b> " + device.status + " (<a href=\"#\" OnClick=toggleDevice(" + device.id + ")>Turn on</a>)";
        //deviceDiv.setAttribute("class", "device off");
    }
    deviceDiv.appendChild(deviceStatus);

    var deviceDescription = document.createElement("span");
    deviceDescription.innerHTML = "<b>Comments:</b> " + device.description;
    deviceDiv.appendChild(deviceDescription);

    var removeDevice = document.createElement("span");
    removeDevice.setAttribute("class", "removeDevice");
    removeDevice.innerHTML = "<a href=\"#\" OnClick=removeDevice(" + device.id + ")>Remove device</a>";
    deviceDiv.appendChild(removeDevice);
}

function showForm() {
    document.getElementById("addDeviceForm").style.display = '';
}

function hideForm() {
   // document.getElementById("addDeviceForm").style.display = "none";
}

function formSubmit() {
    var form = document.getElementById("addDeviceForm");
    var color = form.elements["car_color"].value;
    hideForm();
    document.getElementById("addDeviceForm").reset();
    addDevice(color);
}

function init() {
   // hideForm();
}