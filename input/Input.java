package com.bowen.library.input;

import com.bowen.library.command.IExecutable;
import com.bowen.library.user.User;

import java.util.Scanner;

public class Input {
    private final Scanner scanner= new Scanner(System.in);

    public String prompt(String prompt) throws QuitException {
        System.out.println(prompt+':');
        System.out.print("->");
        if(!scanner.hasNextLine()){
            //通过异常的方式来表示用户的退出
            throw new QuitException();
        }
        return scanner.nextLine();
    }

    public IExecutable menuAndSelect(User user) {
        while (true){
            //1.需要得到这个角色支持那些命令
            IExecutable[] supportedcommands=user.getSupportedComands();

            showMenu(supportedcommands);

            String selectStr=prompt("请输入您的选择");
            try {
                int select = Integer.parseInt(selectStr);
                if(select>=1&&select<=supportedcommands.length){
                    return supportedcommands[select-1];
                }
                System.out.println("请输入正确的序号");
            }catch (NumberFormatException exc){
                System.out.println("请输入正确的数字");
            }
        }
    }

    private void showMenu(IExecutable[] supportedcommands) {
        //2.遍历打印每个命令的名称，显示出来
        System.out.println("==============================");
        for (int i = 0; i < supportedcommands.length; i++) {
            IExecutable command=supportedcommands[i];
            System.out.printf("    %2d. %s\n",i+1,command.getName());
        }
        System.out.println("==============================");
    }
}
