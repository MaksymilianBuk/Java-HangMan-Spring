package com.buk.inzynier.HangMan.services;

import com.buk.inzynier.HangMan.domain.entities.ActualEntity;
import com.buk.inzynier.HangMan.domain.entities.PuzzleEntity;
import com.buk.inzynier.HangMan.exceptions.GameLostException;
import com.buk.inzynier.HangMan.exceptions.GameWonException;
import com.buk.inzynier.HangMan.exceptions.NoGameIdException;
import com.buk.inzynier.HangMan.repositories.ActualRepository;
import com.buk.inzynier.HangMan.repositories.PuzzleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GameService {

    private ActualRepository actualRepository;
    private PuzzleRepository puzzleRepository;

    @Autowired
    public GameService(ActualRepository actualRepository, PuzzleRepository puzzleRepository) {
        this.actualRepository = actualRepository;
        this.puzzleRepository = puzzleRepository;
    }

    public ActualEntity newGameLevel(Integer lifes) {
        Random rand = new Random();
        List<PuzzleEntity> puzzleEntities = puzzleRepository.findAll();
        int randomNumber = rand.nextInt(puzzleEntities.size());

        PuzzleEntity puzzleEntity = puzzleEntities.get(randomNumber);

        String encryptedWord="";
        String password=puzzleEntity.getPassword();
        for(int i=0; i<password.length();i++)
        {
            if(password.charAt(i)==' ')
            {
                encryptedWord+=" ";
            }
            else
            {
                encryptedWord+="_";
            }
        }

        //String encryptedWord = puzzleEntity.getPassword().replaceAll(".", "_");
        ActualEntity actualEntity = new ActualEntity();
        actualEntity.setLifeRemaining(lifes);
        actualEntity.setCategory(puzzleEntity.getCategory());
        actualEntity.setPassword(encryptedWord);
        actualEntity.setPuzzleEntity(puzzleEntity);

        return actualRepository.saveAndFlush(actualEntity);
    }

    public ActualEntity newGame() {
        Random rand = new Random();
        List<PuzzleEntity> puzzleEntities = puzzleRepository.findAll();
        int randomNumber = rand.nextInt(puzzleEntities.size());

        Integer lifes= 5;
        PuzzleEntity puzzleEntity = puzzleEntities.get(randomNumber);

        String encryptedWord="";
        String password=puzzleEntity.getPassword();
        for(int i=0; i<password.length();i++)
        {
            if(password.charAt(i)==' ')
            {
                encryptedWord+=" ";
            }
            else
            {
                encryptedWord+="_";
            }
        }

        //String encryptedWord = puzzleEntity.getPassword().replaceAll(".", "_");
        ActualEntity actualEntity = new ActualEntity();
        actualEntity.setLifeRemaining(lifes);
        actualEntity.setCategory(puzzleEntity.getCategory());
        actualEntity.setPassword(encryptedWord);
        actualEntity.setPuzzleEntity(puzzleEntity);

        return actualRepository.saveAndFlush(actualEntity);
    }

//    public ActualEntity createNewGameCategory(Integer lifes, String category) {
//        Random rand = new Random();
//        List<PuzzleEntity> puzzleEntities = puzzleRepository.findAll();
//
//        for(int i=0;i<puzzleEntities.size();i++)
//        {
//            if(!puzzleEntities.get(i).getCategory().equals(category))
//            {
//                puzzleEntities.remove(i);
//            }
//        }
//        int randomNumber = rand.nextInt(puzzleEntities.size());
//
//        PuzzleEntity puzzleEntity = puzzleEntities.get(randomNumber);
//
//        String encryptedWord="";
//        String password=puzzleEntity.getPassword();
//        for(int i=0; i<password.length();i++)
//        {
//            if(password.charAt(i)==' ')
//            {
//                encryptedWord+=" ";
//            }
//            else
//            {
//                encryptedWord+="_";
//            }
//        }
//
//        //String encryptedWord = puzzleEntity.getPassword().replaceAll(".", "_");
//        ActualEntity actualEntity = new ActualEntity();
//        actualEntity.setLifeRemaining(lifes);
//        actualEntity.setCategory(puzzleEntity.getCategory());
//        actualEntity.setPassword(encryptedWord);
//        actualEntity.setPuzzleEntity(puzzleEntity);
//
//        return actualRepository.saveAndFlush(actualEntity);
//    }

    public ActualEntity tryLetter(Long gameId, Character character) {
        ActualEntity actualEntity= actualRepository.findById(gameId).orElseThrow((NoGameIdException::new));
        String password = actualEntity.getPuzzleEntity().getPassword();
        String passwordEncrypted=actualEntity.getPassword();

        character= Character.toUpperCase(character);

        char[] charArray=passwordEncrypted.toCharArray();
        if (!password.contains(character.toString().toUpperCase())){
            actualEntity.setLifeRemaining(actualEntity.getLifeRemaining()-1);
        } else {
            for(int i=0; i< password.length();i++ ){
                if(password.charAt(i)==character){
                    charArray[i]=character;
                }
            }
            passwordEncrypted=String.valueOf(charArray);
            actualEntity.setPassword(passwordEncrypted);
        }


        if(!passwordEncrypted.contains(String.valueOf('_')))
        {
            if(actualEntity.getLifeRemaining()==0)
            {
                throw new GameLostException();
            }
            else
            {
                throw new GameWonException();
            }
        }

        return actualRepository.saveAndFlush(actualEntity);
    }

    public ActualEntity hintLetter(Long gameId)
    {
        ActualEntity actualEntity= actualRepository.findById(gameId).orElseThrow((NoGameIdException::new));
        //ActualEntity actualEntity= actualRepository.findById(gameId).orElseThrow(()->new RuntimeException("No game found"));
        String password=actualEntity.getPuzzleEntity().getPassword();
        String passwordEncrypted=actualEntity.getPassword();


        char[] charArray=passwordEncrypted.toCharArray();
        for(int i=0;i<password.length();i++)
        {
            if(charArray[i]=='_')
            {
                charArray[i]=password.charAt(i);
                char tempChar=password.charAt(i);
                for(int j=i;j<password.length();j++)
                {
                    if(password.charAt(j)==tempChar)
                    {
                        charArray[j]=tempChar;
                    }
                }
                break;
            }

        }
        passwordEncrypted=String.valueOf(charArray);
        actualEntity.setPassword(passwordEncrypted);


        if(!passwordEncrypted.contains(String.valueOf('_')))
        {
            if(actualEntity.getLifeRemaining()==0)
            {
                throw new GameLostException();
            }
            else
            {
                throw new GameWonException();
            }
        }

        return actualRepository.saveAndFlush(actualEntity);
    }


    public ActualEntity showAnswer(Long gameId)
    {
        ActualEntity actualEntity= actualRepository.findById(gameId).orElseThrow((NoGameIdException::new));
        String password=actualEntity.getPuzzleEntity().getPassword();
        String passwordEncrypted=actualEntity.getPassword();

        char[] charArray=passwordEncrypted.toCharArray();
        for(int i=0;i<password.length();i++)
        {
            if(charArray[i]=='_')
            {
                charArray[i]=password.charAt(i);
                char tempChar=password.charAt(i);
                for(int j=i;j<password.length();j++)
                {
                    if(password.charAt(j)==tempChar)
                    {
                        charArray[j]=tempChar;
                    }
                }
            }
        }
        passwordEncrypted=String.valueOf(charArray);
        actualEntity.setPassword(passwordEncrypted);

        return actualRepository.saveAndFlush(actualEntity);
    }
}
