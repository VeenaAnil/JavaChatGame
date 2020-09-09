# JavaChatGame

Having a Player class - an instance of this class with that can communicate with other Player(s) (other instances of this class)

The use case for this task is as below:

1. create 2 players
2. one of the players should send a message to second player (let's call this player "initiator")
3. when a player receives a message should send back a new message that contains the received message concatenated with the
message counter that this player sent.
4. finalize the program (gracefully) after the initiator sent 10 messages and received back 10 messages (stop condition)
5. both players should run in the same java process (strong requirement)
