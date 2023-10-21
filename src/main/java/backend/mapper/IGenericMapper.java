package backend.mapper;

import backend.exception.CustomException;

public interface IGenericMapper<T,K,L> {
	T toEntity(K k) throws CustomException;
	L toResponse(T t);
}
