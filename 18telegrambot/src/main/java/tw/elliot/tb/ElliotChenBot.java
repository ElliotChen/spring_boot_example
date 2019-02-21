package tw.elliot.tb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
public class ElliotChenBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.username}")
    private String username;

    @Value("${telegram.bot.token}")
    private String token;

    @Override
    public void onUpdateReceived(Update update) {
        Message message = null;
        Long chatId = null;
        String text = null;

        if (update.hasChannelPost()) {
            message = update.getChannelPost();
        } else {
            message = update.getMessage();
        }

        chatId = message.getChatId();
        text = message.getText();

        log.info("ChatId [{}]", chatId);
        log.info("MessageId [{}]", message.getMessageId());
        log.info("Message Content [{}]", text);

        this.sendDummyMessage(chatId);


    }

    @Override
    public String getBotUsername() {
        log.info("BotUsername[{}]", this.username);
        return this.username;
    }

    @Override
    public String getBotToken() {
        log.info("BotToken[{}]", this.token);
        return this.token;
    }


    public void sendDummyMessage(Long chatId) {
        SendMessage sm = new SendMessage();
        sm.setChatId(chatId);
        sm.setText("OKOK!");

        try {
            this.execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
