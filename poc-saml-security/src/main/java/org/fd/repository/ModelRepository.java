package org.fd.repository;

import org.fd.model.Model;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Contract for data access operations on {@link Model}.
 */
@NoRepositoryBean
public interface ModelRepository<T extends Model> extends PagingAndSortingRepository<T, Long>
{
}
