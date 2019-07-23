# Sample Bot

## Local development

### Requirements

- Java11
- Messaging API Channel
  1. 任意の LINE アカウントでテスト用の [Messaging API](https://developers.line.biz/ja/services/messaging-api/) チャネルを作成する
  1. チャネル基本設定 > Webhook 送信 > **利用する** を選択
  1. 環境変数の設定
     1. `~/.bash_profile` や `~/.bashrc` など任意の設定ファイルに、Messaging API チャネルに記載されている Channel Secret 及びアクセストークン、リソースホストを追加する
        ```sh
        export LINE_BOT_CHANNEL_SECRET="123"
        export LINE_BOT_CHANNEL_TOKEN="123456"
        export RESOURCE_HOST="https://abc.ngrok.io"
        ```
        - 💡IDE で Spring Boot を起動する場合は、環境変数変更後は再起動する必要がある
- ngrok
  1. [ngrok: Setup & Installation](https://dashboard.ngrok.com/get-started)

### RUN
1. Git Clone
    ```sh
    git clone https://github.com/Ryoma0622/sample-bot.git
    ```
1. Run
    ```sh
    ./gradlew bootRun
    ```
1. Execute ngrok
   1. `ngrok http 8080`  
     
      Note: When you execute the command, it looks like this.
      ```sh
      ngrok by @inconshreveable                                                                                                                                                                                                     (Ctrl+C to quit)

      Session Status                online
      Account                       ***** (Plan: Free)
      Version                       2.3.34
      Region                        United States (us)
      Web Interface                 http://127.0.0.1:4040
      Forwarding                    http://abcdefgh.ngrok.io -> http://localhost:8080
      Forwarding                    https://abcdefgh.ngrok.io -> http://localhost:8080

      Connections                   ttl     opn     rt1     rt5     p50     p90
                                    0       0       0.00    0.00    0.00    0.00


      ```
   1. コンソールに表示された Forwarding の URL を確認する
      ```sh
      Forwarding https://abc.ngrok.io -> http://localhost:8080
      ```
1. Setting Messaging API
   1. チャネル基本設定 > Webhook URL > **abc.ngrok.io/callback** を設定
   1. 「接続確認」を押し問題がないことを確認する
      - 💡ngrok の URL は実行する度に変わるので都度変更する必要がある

## Sample to return an input message
- Using Return
    ```java
    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        return TextMessage.builder().text(event.getMessage().getText()).build();
    }
    ```
    
- Using LINE Client 
    ```java
    private final LineMessagingClient client;  
    
    @EventMapping
    public void handleTextMessageEventUsingClient(MessageEvent<TextMessageContent> event) {
        client.replyMessage(new ReplyMessage(event.getReplyToken(), TextMessage.builder().text(event.getMessage().getText()).build()));
    }
    ```

**Note: Both do the same**
