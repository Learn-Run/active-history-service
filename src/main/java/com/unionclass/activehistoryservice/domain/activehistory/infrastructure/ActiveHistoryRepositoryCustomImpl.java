package com.unionclass.activehistoryservice.domain.activehistory.infrastructure;

import com.unionclass.activehistoryservice.common.response.CursorPage;
import com.unionclass.activehistoryservice.domain.activehistory.dto.out.GetActiveHistoryResDto;
import com.unionclass.activehistoryservice.domain.activehistory.entity.ActiveHistory;
import com.unionclass.activehistoryservice.domain.activehistory.enums.ActiveHistoryType;
import lombok.RequiredArgsConstructor;
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
    public CursorPage<GetActiveHistoryResDto> findByCursor(String memberUuid, ActiveHistoryType type, String cursorId, int page, int size) {

        Query query = createBaseQuery(memberUuid, type);

        query.addCriteria(Criteria.where("_id").lt(cursorId));
        query.limit(size);

        List<GetActiveHistoryResDto> result = mongoTemplate.find(query, ActiveHistory.class)
                .stream().map(GetActiveHistoryResDto::from).toList();

        String nextCursor = !result.isEmpty() ? result.get(result.size() - 1).getUuid() : null;
        String prevCursor = !result.isEmpty() ? result.get(0).getUuid() : null;

        return CursorPage.of(result, size, page, nextCursor, prevCursor);
    }

    @Override
    public String findCursorByOffset(String memberUuid, ActiveHistoryType type, int offset) {

        Query query = createBaseQuery(memberUuid, type);

        query.skip(offset).limit(1);
        List<ActiveHistory> result = mongoTemplate.find(query, ActiveHistory.class);

        return result.isEmpty() ? null : result.get(0).getId();
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