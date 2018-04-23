package ru.kek.memehouse.dao.repos;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import ru.kek.memehouse.models.Meme;

import java.util.List;

/**
 * gordeevnm@gmail.com
 * 14.01.18
 */
public interface MemesRepo extends CrudRepository<Meme, Long> {
	@NotNull List<Meme> findAllById(@NotNull Iterable<Long> ids);
}
