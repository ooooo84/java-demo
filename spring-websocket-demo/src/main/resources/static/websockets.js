var stompClient = null;

$(document).ready(function () {
    console.log('Index page is ready');

    connect();

    $('#send').click(function (event) {
        sendMessage();
        $('#message').val('');
        event.preventDefault();
    });

    $('#send-private').click(function (event) {
        sendPrivateMessage();
        $('#private-message').val('');
        event.preventDefault();
    });
});

function connect() {
    var socket = new SockJS('/our-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ', frame);

        // 订阅全局Destination
        stompClient.subscribe('/topic/messages', function (message) {
            console.log('Receive message: ', message)
            showMessage(JSON.parse(message.body).content);
        });

        // 订阅用户特定的destination，前面加/user
        stompClient.subscribe('/user/topic/private-messages', function (message) {
            console.log('Receive private message: ', message)
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

function sendPrivateMessage() {
    console.log('Sending private message');
    stompClient.send('/ws/private-message', {}, JSON.stringify({'messageContent': $('#private-message').val()}));
}