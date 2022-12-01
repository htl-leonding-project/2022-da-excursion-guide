package at.htl.leotour_backend.Dto;

import at.htl.leotour_backend.entity.Activity;
import at.htl.leotour_backend.entity.Topic;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ActivityDto(
        long id,
        String activityName,
        LocalDateTime startDateTime,
        double longitude,
        double latitude,
        long previousActivityId,
        String comment,
        boolean isPublic,
        LocalDate publicationDate,
        long topicId
) {}
