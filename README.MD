<h2>Переменные среды</h2>
<p>Часть свойст приложения из файла application.properties берутся из переменной среды (окружения) ОС.</p>
<p>Команда для Windows (протестировано на Windows 10) для установки переменной среды "geek_market_user" значения "userOfSpring" для локального пользователя:</p>
<code>C:\>setx geek_market_user "userOfSpring"</code>
<p>Далее необходимо перезагрузить компьютер.</p>
<p>Для просмотра результатов на Windows можно использовать команду</p>
<code>C:\>echo %geek_market_user%</code>

<p>Команда для Linux (протестировано на Ubuntu 18.04.1 LTS) для установки переменной окружения "geek_market_user" значения "userOfSpring":</p>
<code># echo 'export geek_market_user="userOfSpring"' >> /etc/environment</code>
<p>Далее необходимо перезайти в систему.</p>
<p>Для проверки результатов можно использовать команду:</p>
<code># printenv geek_market_password</code>

