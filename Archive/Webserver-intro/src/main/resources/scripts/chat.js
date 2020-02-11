const socket = new WebSocket("ws://localhost:8080");
socket.onmessage = message => {
    const newElement = document.createElement("div");
    newElement.innerHTML = `<p>${message.data}</p>`;

    document
        .getElementById("messages")
        .appendChild(newElement);
};

const sendMessage = function()
{
    const form = document.getElementById("sendMessageForm");
    const text = form.elements["text"].value;

    socket.send(text);

    document.getElementById("sendMessageForm").reset();
};
