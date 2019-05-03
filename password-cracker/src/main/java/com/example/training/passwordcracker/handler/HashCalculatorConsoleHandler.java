package com.example.training.passwordcracker.handler;

import com.example.training.common.handler.Handler;
import com.example.training.common.handler.Printer;
import com.example.training.common.handler.REPLFunction;
import com.example.training.common.handler.Reader;
import com.example.training.common.service.DateTimeService;
import com.example.training.passwordcracker.service.PasswordCracker;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

import static com.example.training.passwordcracker.constant.HashCalculatorConstants.PASSWORD_CALCULATION_TIMEOUT_MIN;

@Log4j2
public class HashCalculatorConsoleHandler extends Handler {

    private final DateTimeService dateTimeService;
    private final PasswordCracker passwordCracker;

    @Builder
    public HashCalculatorConsoleHandler(Printer printer, Reader reader,
                                        DateTimeService dateTimeService, PasswordCracker passwordCracker) {
        super(printer, reader);
        this.dateTimeService = dateTimeService;
        this.passwordCracker = passwordCracker;
    }

    private void printGreetings() {
        printer.println("Hi, user!");
        printer.println("Welcome to my password cracker app.");
        printer.println("You pass the password MD5 hash and I try to guess the original password");

        printer.println();
    }

    private void crackAndPrintPassword() {
        printer.println("So, lets begin");

        String hash = new REPLFunction<String, String>(printer, reader)
                .withLoop()
                .withPattern(Pattern.compile("\\w{32}"))
                .withErrorMessage("Please enter MD5 hash in proper format ([a-zA-Z0-9]{32} string)")
                .eval();

        printer.printf("Cracking %s started at %s", hash, dateTimeService.format(LocalDateTime.now()));
        printer.println();

        try {
            String password = passwordCracker.crack(hash);

            printer.printf("Cracking finished at %s. Your password was %s",
                    dateTimeService.format(LocalDateTime.now()), password);
            printer.println();

        } catch (InterruptedException e) {
            log.error("An error occurred while cracking password hash {}. ErrorMessage: ", hash, e);
            printer.println(e.getMessage());

        } catch (ExecutionException e) {
            log.error("An error occurred during parallel computations. ErrorMessage: ", e);
            printer.println(e.getMessage());

        } catch (TimeoutException e) {
            log.error("Password cracking timeout exceeded. ErrorMessage: ", e);
            printer.printf("Wow! Cracking took longer than %s minutes. You have a really strong password!",
                    PASSWORD_CALCULATION_TIMEOUT_MIN);
            printer.println();
        }

        printer.println();
    }

    private void printGoodbye() {
        printer.println("Thank you for using my password cracker.");
        printer.println("Good luck!");
    }

    @Override
    public void handle(String[] args) {
        printGreetings();
        crackAndPrintPassword();
        printGoodbye();
    }
}
