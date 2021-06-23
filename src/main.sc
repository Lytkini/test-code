
theme: /

    state: Buttons
        q: *
        q!: кнопки
        a: Примеры различных типов ответов и проверка репы
        script:
            var reply = {
                    "type":"buttons",
                    "buttons":[
                      {
                        "text": "Ссылка",
                        "url": "https://lytkini.com"
                      },
                      {
                        "text": "Переход к состоянию",
                        "data": "/Приветствие",
                        "some_field": "просто стрёмное поле"
                      },
                    ]
                };
            var text = {
                           "type":"text",
                           "text":"Текстовый ответ в диалоге с пользователем",//Реплика, которая отобразится в чате
                           //"tts":"+7 (909) 2282424",//Реплика, которую ассистент произнесет, если не задана реплика ssml
                           //"ssml":"<speak><say-as interpret-as=\"money\" format=\"genitive\" detail=\"say-null-cents\">10</say-as></speak>",//Реплика, с ssml интонациями
                           //"ssml":"<speak><audio text=\"м?\"/> Что?</speak>",
                           //"ssml":"<speak>Какие <sub alias=\"тащемта\">счета</sub> открыты?</speak>",
                           "ssml":"Привет $request.query!<break time=\"800ms\" /> Кажется, на ваших <sub alias=\"щетах\">счетах</sub> <say-as interpret-as=\"money\" detail=\"JPY_short-form\">21,15</say-as> <audio text=\"м?\"/>?",
                        };
             var cardList = {
                   "type": "cardList",
                   "title": "Заголовок карточки",
                   "subtitle": "Описание",
                   "cells": [{
                           "title": "Заголовок ячейки 1",
                           "subtitle": "Описание ячейки 1",
                           "value": "Значение, которое отображается в правой части ячейки 1. Рекомендуемая длина менее 5 символов.",
                           "iconUrl": "https://content.sberdevices.ru/smartmarket-smide-prod/1624/1625/7eKpelIhRNYfks9l.png",
                           "hash": "36ec46e26b2051f909baf1766b6544f2",
                           "action": {
                               "url": "Веб-адрес, который откроется при нажатии ячейки"
                               },
                           },
                           {
                           "title": "Заголовок ячейки 2",
                           "subtitle": "Описание ячейки 2",
                           "value": "Правая часть",
                           "iconUrl": "https://content.sberdevices.ru/smartmarket-smide-prod/1624/1625/7eKpelIhRNYfks9l.png",
                           "hash": "36ec46e26b2051f909baf1766b6544f2",
                           "action": {
                               "text": "Текст, который отправится при нажатии ячейки"
                               },
                           }],
                   "buttons": [
                           {
                             "text": "Название кнопки карточки",
                             "url": "Веб-адрес, который откроется при нажатии кнопки"
                           }],
                   "auto_listening": false
                 };
             var card = {
                 "type": "card",
                 "title": "title",
                 "description": "descr",
                 "imageUrl": "https://content.sberdevices.ru/smartmarket-smide-prod/1624/1625/XJHvVyOTzh2DQmrY.jpg",
                 //"hash":"<hash картинки из раздела Контент>",
                 "button": {
                    "text": "text",
                    "url": "url"
                 },
                 "auto_listening": false
             };
            $response.replies = $response.replies || [];
            $response.replies.push(card);
            $response.replies = $response.replies || [];
            $response.replies.push(cardList);
            $response.replies = $response.replies || [];
            $response.replies.push(reply);
            $response.replies = $response.replies || [];
            $response.replies.push(text);
            $response.replies.push({
              type: "image",
              imageUrl: "https://content.sberdevices.ru/smartmarket-smide-prod/1624/1625/XJHvVyOTzh2DQmrY.jpg",
              //hash: "5debe321a4cc700c9ba138edd5e98f71",
             })
        buttons:
            "Поиск" -> /поискСущностей
            "Подсказка" -> /Приветствие
        buttons:
            "Имя кнопки" -> /NormalButtons/2
            "подстановка параметров как в {{ 'имени' }} так и в пути для перехода" -> {{ './3' }}
            "Пустая кнопка"
        buttons:
          "Привет" -> ../Hello
          {text:"Отправить контакты", request_contact: true}  // две кнопки будут расположены на одной строке
        buttons:
          "Вторая кнопка"

    state: Приветствие
        intent!: /привет
        a: Ну что сказать! {{$request.query}}
        script:
            var reply = {
                           "type":"text",
                           "text":"$request.query эту фразу ассистент покажет на экране устройства",//Реплика, которая отобразится в чате
                           //"tts":"+7 (909) 2282424",//Реплика, которую ассистент произнесет, если не задана реплика ssml
                           //"ssml":"<speak><say-as interpret-as=\"money\" format=\"genitive\" detail=\"say-null-cents\">10</say-as></speak>",//Реплика, с ssml интонациями
                           //"ssml":"<speak><audio text=\"м?\"/> Что?</speak>",
                           //"ssml":"<speak>Какие <sub alias=\"тащемта\">счета</sub> открыты?</speak>",
                           "ssml":"Привет $request.query!<break time=\"800ms\" /> Кажется, на ваших <sub alias=\"щетах\">счетах</sub> <say-as interpret-as=\"money\" detail=\"JPY_short-form\">21,15</say-as> <audio text=\"м?\"/>?",
                        };
            $response.replies = $response.replies || [];
            $response.replies.push(reply);
            
    state: поискСущностей
        q!: entitiesLookup
        script:
            $reactions.answer(JSON.stringify($caila.entitiesLookup("машина", true)));

    state: Прощание
        intent!: /пока
        a: Пока пока

    state: Fallback
        event!: noMatch
        a: Я не понял. Вы сказали: {{$request.query}}
