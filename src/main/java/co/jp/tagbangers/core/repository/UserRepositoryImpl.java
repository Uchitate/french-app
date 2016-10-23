package co.jp.tagbangers.core.repository;

import co.jp.tagbangers.core.entity.User;
import co.jp.tagbangers.core.model.UserSearchRequest;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<User> search(UserSearchRequest request) {
		List<User> users;
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);

		List<Predicate> where = new ArrayList<>();
		if (StringUtils.hasText(request.getName())) {
//			where.add(builder.equal(root.get(User_.name), request.getName()));
		}
		if (request.getAgeStart() != null && request.getAgeEnd() != null) {
//			where.add(builder.between(root.get(User_.age), request.getAgeStart(), request.getAgeEnd()));
		}
		if (request.getGender() != null) {
//			where.add(builder.equal(root.get(User_.gender), request.getGender()));
		}

		query.where(where.toArray(new Predicate[where.size()]));

		return entityManager.createQuery(query).getResultList();
	}
}
