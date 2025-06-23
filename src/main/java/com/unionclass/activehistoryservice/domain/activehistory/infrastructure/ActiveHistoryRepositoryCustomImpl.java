package com.unionclass.activehistoryservice.domain.activehistory.infrastructure;

import com.unionclass.activehistoryservice.domain.activehistory.dto.out.GetActiveHistoryResDto;
import com.unionclass.activehistoryservice.domain.activehistory.entity.ActiveHistory;
import com.unionclass.activehistoryservice.domain.activehistory.enums.ActiveHistoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ActiveHistoryRepositoryCustomImpl implements ActiveHistoryRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public PageImpl<GetActiveHistoryResDto> findByOffset(String memberUuid, ActiveHistoryType type, Pageable pageable) {

        Query query = createBaseQuery(memberUuid, type);

        query.with(pageable);

        List<GetActiveHistoryResDto> result = mongoTemplate.find(query, ActiveHistory.class)
                .stream().map(GetActiveHistoryResDto::from).toList();

        long totalElements = mongoTemplate.count(createBaseQuery(memberUuid, type), ActiveHistory.class);

        return new PageImpl<>(result, pageable, totalElements);

    }

    private Query createBaseQuery(String memberUuid, ActiveHistoryType type) {

        Query query = new Query();

        query.addCriteria(Criteria.where("memberUuid").is(memberUuid));
        if (type != null) {
            query.addCriteria(Criteria.where("type").is(type));
        }
        query.with(Sort.by(Sort.Direction.DESC, "_id"));

        return query;
    }
}