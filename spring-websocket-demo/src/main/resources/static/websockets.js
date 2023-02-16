var stompClient = null;

$(document).ready(function () {
    console.log('Index page is ready');

    connect();

    $('#send').click(function (event) {
        sendMessage();
        $('#message').val('');
        event.preventDefault();
    });
});

function connect() {
    var socket = new SockJS('/our-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ', frame);

        // 订阅特定Destination
        stompClient.subscribe('/topic/messages', function (message) {
            console.log('Receive message: ', message)
            showMessage(JSON.parse(message.body).content);
        });
    });
}

function showMessage(message) {
    $('#messages').append("<tr><td>" + message + "</td></tr>");
}

function sendMessage() {
    console.log('Sending message');
    stompClient.send('/ws/message', {}, JSON.stringify({'messageContent': $('#message').val()}));
}