<template>
    <v-container>
        <v-row justify="center">
            <v-col cols="12" md="8">
                <v-card>
                    <v-card-title class="text-center text-h5">
                        채팅
                    </v-card-title>
                    <v-card-text>
                        <div class="chat-box">
                            <div 
                                v-for="(msg, index) in messages"
                                :key="index"
                                :class="['chat-message', msg.senderEmail === this.senderEmail ? 'sent' : 'received']"
                            >
                                <strong>{{ msg.senderEmail }}: </strong> {{ msg.message }}
                            </div>
                        </div>
                        <v-text-field
                            v-model="newMessage"
                            label="메시지 입력"
                            @keyup.enter="sendMessage"
                        />
                        <v-btn color="primary" block @click="sendMessage">전송</v-btn>
                    </v-card-text>
                </v-card>

            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
// import axios from "axios";

export default {
    data() {
        return {
            messages: [],
            newMessage: "",
            stompClient: null,
            token: "",
            senderEmail: null,
        }
    },
    created() {
        this.senderEmail = localStorage.getItem("email");
        this.connectWebsocket();
    },
    // 사용자가 현재 라우트에서 다른 라우트로 이동하려고 할 때 
    beforeRouteLeave(to, from, next) {
        this.disconnectWebsocket();
        next();
    },
    // 화면을 완전히 꺼버렸을 때
    beforeUnmount() {
        this.disconnectWebsocket();
    },
    methods: {
        connectWebsocket() {
            if (this.stompClient && this.stompClient.connected) {
                return;
            }

            // sockJs는 websocket을 내장한 향상된 js 라이브러리. http 엔트포인트 사용.
            const sockJs = new SockJS(`${process.env.VUE_APP_API_BASE_URL}/connect`);
            this.stompClient = Stomp.over(sockJs);
            this.token = localStorage.getItem("token");
            this.stompClient.connect(
                {
                    Authorization: `Bearer ${this.token}`
                },
                () => {
                    this.stompClient.subscribe(`/topic/1`, (message) => {
                        const parseMessage = JSON.parse(message.body);
                        this.messages.push(parseMessage);
                        this.scrollToBottom();
                    })
                }
            )
        },
        sendMessage() {
            if (this.newMessage.trim() === "") {
                return;
            }
            const message = {
                message: this.newMessage,
                senderEmail: this.senderEmail,
            }
            this.stompClient.send(`/publish/1`, JSON.stringify(message));
            this.newMessage = "";
        },
        scrollToBottom() {
            this.$nextTick(() => {
                const chatBox = this.$el.querySelector(".chat-box");
                chatBox.scrollTop = chatBox.scrollHeight;
            })
        },
        disconnectWebsocket() {
            if (this.stompClient && this.stompClient.connected) {
                this.stompClient.unsubscribe(`/topic/1`);
                this.stompClient.disconnect();
            }
        }
    }
}
</script>

<style>
.chat-box {
    height: 300px;
    overflow-y: auto;
    border: 1px solid #ddd;
    padding: 10px;
    margin-bottom: 10px;
}
.chat-message {
    margin-bottom: 10px;
}
.sent {
    text-align: right;
}
.received {
    text-align: left;
}
</style>