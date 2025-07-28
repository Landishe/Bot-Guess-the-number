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
        script:
            # сохраняем введенное пользователем число
            var num = $parseTree._Number;
            # проверяем угадал ли пользователь загаданное число и выводим соответствующую реакцию
            if(num == $session.number) {
                $reactions.answer("Ты выиграл! Хочешь еще раз?");
                $reactions.transition("/Правило/Согасен?");
            }
                else 
                    if (num < $session.number)
                        $reactions.answer(selectRandomArg (["Мое число больше!", "Бери выше", "Попробуй число еще раз"]));
                    else $reactions.answer(SelectRandomArg(["Мое число меньше!", "Подсказка: число меньше", "Дам тебе еще одну попытку! Мое число меньше."]));
            
    state: NoMatch  || context = true
        event!: noMatch
            random:
                a: я не понял
                a: Что вы имеете в виду?
                a: Ничего не пойму

