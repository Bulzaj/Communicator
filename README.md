<h1>Communicator App</h1>

<p>Simple communicator app that allow sending text messages (and other attachments in future) in real time connection and saving conversation history.</p>

<h1>How does it works</h1>
<ol>
<li>User is able to create new account protected by basic auth security (cridentials: login and password)</li>
<li>When user is trying to log in, server generate JWT, based on user's cridentials, wchich is stored in session in client app</li>
<li>JWT is sent in every request via http header</li>
<li>When deserialized token is correct (Spring Security filter chain returns positive result), user is able to use server's REST API e.g. send message</li>
<li> Sending messages process:</li>
<ol>
<li>When user fill message's editor input field and press send button, message is stored in database by http post method</li>
<li>In same time, both participants of conversation subscribes appropriate websocket connection.</li>
<li>For that moment, users are able to sending messages in real time, simultaneously storing history of conversation in DB</li>
</ol>
</ol>

<h1>Technology stack</h1>
<p>Backend Technologies:</p>
<ul>
<li>Spring boot</li>
<li>Spring Security (Authentication and authorization)</li>
<li>Spring Data, JPA, MySQL, MySQL connector (DB implementation and connection)</li>
<li>JWT implementaion (Generating and decoding auth tokens)</li>
<li>Websocket implementation (Creating and real time connection between clients</li>
</ul>
<p>Frontend technologies:</p>
<ul>
<li>Angular</li>
<li>ReactiveX</li>
<li>Own HTML/CSS template (replaced with angular materials in the future)</li>
</ul>

