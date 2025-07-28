require: slotfilling/slotfilling.sc
    module = sys.zb-common

require: common.js
    module = sys.zb-common

theme: /
    
    state: Правила
        q!: $regex</start>
        intent!: /Давай поиграем
        a: Игра больше-меньше. Загадаю число от 0 до 100, ты будешь отгадывать. Начнем?
        go!: /Правила/Согласен?

        state: Согласен?

            state: да
                intent: /Согласие
                go!: /Игра
            
            state: нет
                intent: /Несогласие
                a: а ну ладно! Если Передумаешь - скажи "давай поиграем"

    state: Игра
        script:
            $session.number = $jsapi.random(100) + 1;
            # reactions.answer("Загаданно{{$session.number}}");
            $reactions.transition("/Проверка");

    state: Проверка
        intent: /Число
        
    state: NoMatch  || noContext = true
        event!: noMatch
            random:
                a: я не понял
                a: Что вы имеете в виду?
                a: Ничего не пойму

