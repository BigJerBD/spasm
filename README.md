Twitch Scala IRC BOT
===============
this is a toy project used to learn a little bit of metaprogramming in scala

this code contains a simple scala tool to record data from IRC (twitch chat, slack, etc.) 
and record it into log files

to do this it logs a bot that connect itself to a channel, then gather data

this was mainly made to learn and experiment code and implementation of code in scala

## Getting Started
These Instruction will explain the requirements and how to use this tool
### Prerequisites
This project uses Maven's packing systems with Scala and Java.

So, make sure you have Maven and  minimaly openjdk-8-jdk on your device
### Installing
to build the program, run :
```
mvn package
```

## Usage 

to run your bot with a config, run in a terminal :
```
java -cp target/spasm-1.0-jar-with-dependencies.jar Spasm <path_to_yaml>
```

to create a configuration file, please refer to the examples in the etc folder.
for developper, if you want to add new bots and listener, simply add them in 
`src/main/scala/listeners` and `src/main/scala/ircbots`.
this can then added in the configuration yaml as a type for `bots:` and `listeners`


## References

* [PircBotx](https://github.com/pircbotx/pircbotx) - Java ircbot library used to listen to irc chats
 
## Authors

* **Jérémie Bigras-Dunberry** - *Initial work* 




