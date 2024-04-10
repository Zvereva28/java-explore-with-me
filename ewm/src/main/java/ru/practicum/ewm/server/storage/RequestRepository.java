package ru.practicum.ewm.server.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.server.models.request.ParticipationRequest;
import ru.practicum.ewm.server.models.request.RequestStatus;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<ParticipationRequest, Long> {

    ParticipationRequest findByEventAndRequester(Long event, Long requester);

    List<ParticipationRequest> findAllByRequester(Long requester);

    List<ParticipationRequest> findAllByEvent(Long event);

    List<ParticipationRequest> findAllByStatusOrderByIdDesc(RequestStatus status);

    @Query(value = "select * " +
            "from participation_requests pr " +
            "where id IN (:ids) " +
            "and status = 'PENDING'",
            nativeQuery = true)
    List<ParticipationRequest> findAllByIds(@Param("ids") List<Long> ids);



}