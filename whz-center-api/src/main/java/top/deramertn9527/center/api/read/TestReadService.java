package top.deramertn9527.center.api.read;

import top.deramertn9527.center.api.doman.Test;
import top.deramertn9527.center.api.exception.WhzCenterApiException;

public interface TestReadService {

    Test findById(Long id) throws WhzCenterApiException;
}
