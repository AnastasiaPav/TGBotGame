import org.telegram.telegrambots.ApiContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Bot extends TelegramLongPollingBot{

    public static final String PROXY_HOST = "u0k12.tgproxy.me";
    public static final int PROXY_PORT = 1080;
    public static Words words;
    public static void main(String[] args) throws TelegramApiRequestException, FileNotFoundException {
        ApiContextInitializer.init();

        /*TelegramLongPollingBot bot = new Bot();
        String proxyHost = "80.211.181.37";
        int proxyPort = 3128;
        int timeout = 75 * 1000;

        RequestConfig requestConfig = RequestConfig.custom()
                .setProxy(new HttpHost(proxyHost, proxyPort))
                        .setConnectTimeout(timeout)
                        .setConnectionRequestTimeout(timeout)
                        .setConnectTimeout(timeout)
                        .build();
        bot.getOptions().setRequestConfig(requestConfig);*/


        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        //telegramBotsApi.registerBot(bot);

        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
        //CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

        /*credentialsProvider.setCredentials(
                new AuthScope(PROXY_HOST, PROXY_PORT),
                new UsernamePasswordCredentials("", ""));*/

        //HttpHost httpHost = new HttpHost(PROXY_HOST, PROXY_PORT);
        //RequestConfig requestConfig = RequestConfig.custom().build();
        //botOptions.setRequestConfig(requestConfig);
        //botOptions.setHttpProxy(httpHost);
        words = new Words();
        try{
            telegramBotsApi.registerBot(new Bot());
        }catch (TelegramApiRequestException e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    private void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();

        sendMessage.enableMarkdown(true);//возможность разметки
        sendMessage.setChatId(message.getChatId().toString());// в какой именно чат отправлять
        sendMessage.setReplyToMessageId(message.getMessageId());//на какое именно сообщение отвечать(его айди)
        sendMessage.setText(text);
        try{
            sendMessage(sendMessage);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {//для приема сообщений
        Message message = update.getMessage();

        if(message != null && message.hasText()){
            switch (message.getText()){
                case "/help":
                    sendMsg(message, "How can I help you? You need to write a word.");
                    break;
                case "/settings":
                    sendMsg(message, "What will we do?");
                    break;
                case "/start":
                    sendMsg(message, "Hello, let's start!");
                    break;
                default:
                    try {
                        sendMsg(message,words.gameLogic(message.getText()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    @Override
    public String getBotUsername() {//вернуть имя, указанное при регистрации
        return "WordsBat";
    }

    @Override
    public String getBotToken() {
        return "658562540:AAEVff2fxFCeqaWMWSw2GU51lydpHooTFZE";
    }
}
