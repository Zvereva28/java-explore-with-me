package ru.practicum.ewm.server.models.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.practicum.ewm.server.models.category.Category;
import ru.practicum.ewm.server.models.location.Location;
import ru.practicum.ewm.server.models.user.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "events", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "annotation", nullable = false)
    private String annotation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "created_on")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn = LocalDateTime.now();

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "event_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "initiator_id")
    private User initiator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "paid", nullable = false)
    private Boolean paid = false;

    @Column(name = "participant_limit")
    private Integer participantLimit;

    @Column(name = "confirmed_requests")
    private Integer confirmedRequests = 0;

    @Column(name = "available")
    private Boolean available = true;

    @Column(name = "published_on")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishedOn;

    @Column(name = "request_moderation")
    private Boolean requestModeration;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private EventState state = EventState.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(name = "state_action")
    private StateAction stateAction = StateAction.SEND_TO_REVIEW;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "views")
    private Integer views = 0;

}

