package ru.eatthefrog.hatterBot.VkSpy.VkRequestsLogic.VkSpecialRequests;

import org.springframework.beans.factory.annotation.Autowired;
import ru.eatthefrog.hatterBot.ExternalApiProvider.ApiProvider;
import ru.eatthefrog.hatterBot.ExternalApiProvider.BotTokenProvider;
import ru.eatthefrog.hatterBot.VkSpy.VkApi.VkApiMethodsImplementator;
import ru.eatthefrog.hatterBot.VkSpy.VkNameProvider;
import ru.eatthefrog.hatterBot.VkSpy.VkProfileManager.VkProfileUnitManager;
import ru.eatthefrog.hatterBot.VkSpy.VkTokenManager.VkUserTokenManager;

import javax.annotation.PostConstruct;

public abstract class VkSpyRequestAbstract {
    @Autowired
    VkUserTokenManager vkUserTokenManager;
    @Autowired
    VkProfileUnitManager vkProfileUnitManager;
    @Autowired
    VkApiMethodsImplementator vkApiMethodsImplementator;
    @Autowired
    VkNameProvider vkNameProvider;
    @Autowired
    ApiProvider apiProvider;
    @Autowired
    BotTokenProvider botTokenProvider;

    long lastTimeChecked;
    int spyVkId;
    int checkFrequency = 10;
    int chatId;
    boolean queued;

    public abstract void handle();
    public abstract String getSpyPrompt();

    @PostConstruct
    public void updateTime() {
        this.lastTimeChecked = System.currentTimeMillis();
    }

    public boolean isQueued() {
        return this.queued;
    }

    public void setQueued() {
        this.queued = true;
    }

    public void setUnQueued() {
        this.queued = false;
    }

    public boolean isTokenReady() {
        return this.vkUserTokenManager.getToken(
                this.chatId
        ).isReady();
    }

    public boolean shouldUpdate() {
        boolean toReturn =  System.currentTimeMillis() - lastTimeChecked > 1000*this.checkFrequency;
        if (toReturn) {
            this.lastTimeChecked = System.currentTimeMillis();
        }
        return toReturn;
    }

    public void setSpyVkId(int spyVkId) {
        this.spyVkId = spyVkId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }
    public int getChatId() {
        return this.chatId;
    }
    public int getSpyVkId() {
        return this.spyVkId;
    }
}
