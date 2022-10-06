package com.bike.retoBikes.Service;

import com.bike.retoBikes.Model.Score;
import com.bike.retoBikes.Repository.ScoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepo scoreRepo;

    public List<Score> getAll() {
        return scoreRepo.getAll();
    }

    public Optional<Score> getScore(int scoreId) {
        return scoreRepo.getScore(scoreId);
    }

    public Score save(Score score) {
        if (score.getIdScore() == null) {
            return scoreRepo.save(score);
        } else {
            Optional<Score> score1 = scoreRepo.getScore(score.getIdScore());
            if (score1.isPresent()) {
                return score;
            } else {
                return scoreRepo.save(score);
            }
        }
    }

    public Score update(Score score){
        if(score.getIdScore()!=null){
            Optional<Score> g= scoreRepo.getScore(score.getIdScore());
            if(g.isPresent()){
                if(score.getMessageText()!=null){
                    g.get().setMessageText(score.getMessageText());
                }
                if(score.getScore()!=null){
                    g.get().setScore(score.getScore());
                }
                return scoreRepo.save(g.get());
            }
        }
        return score;
    }


    public boolean deleteScore (int id){
        boolean del = getScore(id).map(score -> {
            scoreRepo.delete(score);
            return true;
        }).orElse(false);
        return del;
    }
}
