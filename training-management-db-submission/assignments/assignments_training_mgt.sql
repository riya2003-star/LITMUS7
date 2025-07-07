SELECT Batch.batch_id, cand_status.status FROM Batch
JOIN cand_status ON Batch.batch_id=cand_status.batch_id
WHERE candidate_id=401;

SELECT Trainer.trainer_name FROM Trainer
JOIN Trainer_batch ON Trainer_batch.trainer_id=Trainer.trainer_id
WHERE batch_id=302;

SELECT topic_name FROM Topic
WHERE Course_id=1;

SELECT submit.score FROM submit
JOIN Assignment ON submit.assign_id=Assignment.assign_id
WHERE batch_id=301 AND candidate_id=401;

SELECT candidate_name FROM Candidate 
JOIN cand_status ON Candidate.candidate_id=cand_status.candidate_id
WHERE status='Completed';