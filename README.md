# jchatty

### Критерии приемки:

_Продемонстрирована корректная работа фич "Посылка сообщения" и "Просмотр полной истории"
Продемонстрирована корректная работа альтернативных сценариев этих двух фич
Выполнены внутренные критерии качества
Ключевые решения по дизайну обоснованы, озвучены trade-offs, описаны рассмотренные альтернативы и проведеные исследования
Продемонстрирована работа нагрузочного теста с проверкой корректности
Продемонстрирована работа приложения в профайлере под нагрузкой
Озвучены выявленные узкие места приложения и максимальные выозможные значения performance-характеристик приложения_

- Посылка сообщений и Просмотр полной истории.
- Внутренние критерии качества.
- Нагрузочное тестирование с проверкой корректности.
  - Работа приложения в профайлере под нагрузкой.
  - Выявленние "узких мест" приложения и максимальные возможных значений performance-характеристик приложения.


### Функциональные требования
**Посылка сообщения**
- **User Story** _Я, как пользователь, хочу послать сообщение, чтобы его увидели другие пользователи чата._
- **Acceptance Test**
- **Given** _Запущены клиентское и серверное приложения._
- **When** _Я печатаю и отсылаю сообщение в чат с помощью команды "/snd <сообщение>"_
- **Then** _Я вижу свое сообщение с датой и временем._
- Другие пользователи видят мое сообщение с датой и временем.

**Просмотр полной истории**
- **User Story** _Я, как пользователь, хочу видеть все сообщения за все время существования чата, чтобы найти нужное мне сообщение._
- **Acceptance Test**
- **Given** _Запущены клиентское и серверное приложения._
- **When** _Я ввожу команду "/hist"._
- **Then** _Я вижу полный список всех сообщений чата с датой и временем_

**Идентификация**
- **User Story** _Я, как пользователь, хочу представиться системе, чтобы все участники чата ассоциировали мои сообщения со мной._
- **Acceptance Test**
- **Given** _Запущены клиентское и серверное приложения._
- **When** _Я ввожу команду "/chid <выбранный-ник>"_
- **Then** _Все сообщения от меня снабжаются информацией о моем нике._

**Выбор комнаты**
- **User Story** _Я, как пользователь, хочу менять комнаты в чате, чтобы видеть сообщения только тех пользователей, которые находятся в этой же комнате._
- **Acceptance Test**
- **Given** _Запущены клиентское и серверное приложения. Я идентифицировался._
- **When** _Я ввожу команду "/chroom <имя-комнаты>"._
- **Then** _Я вижу сообщения только от пользователей в этой же комнате. Мои сообщения видят только пользователи в этой же комнате._
