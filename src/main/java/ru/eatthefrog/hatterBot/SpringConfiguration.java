package ru.eatthefrog.hatterBot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("ru.eatthefrog.hatterBot")
@PropertySource("bot.properties")
public class SpringConfiguration {
}