state: Example
    intetnt!: /Число
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
            