package backend.controller;

import backend.exception.CustomException;
import backend.model.dto.request.SizeRequest;
import backend.model.dto.response.SizeResponse;
import backend.service.size.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/size")
@CrossOrigin("*")
public class SizeController {

	@Autowired
	private ISizeService sizeService;
	
	@GetMapping
	public ResponseEntity<Page<SizeResponse>> getAllSize(@PageableDefault(page = 0, size = 3) Pageable pageable, @RequestParam Optional<String> search) {
		return new ResponseEntity<>(sizeService.findAll(pageable, search), HttpStatus.OK);
	}
	
	@GetMapping("/{sizeId}")
	public ResponseEntity<SizeResponse> getSizeById(@PathVariable Long sizeId) throws CustomException {
		return new ResponseEntity<>(sizeService.findById(sizeId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<SizeResponse> addNewSize(@RequestBody SizeRequest sizeRequest) {
		return new ResponseEntity<>(sizeService.save(sizeRequest), HttpStatus.CREATED);
	}
	
	@PutMapping("/{sizeId}")
	public ResponseEntity<SizeResponse> updateSize(@RequestBody SizeRequest sizeRequest, @PathVariable Long sizeId) {
		return new ResponseEntity<>(sizeService.update(sizeRequest, sizeId), HttpStatus.OK);
	}
	
	@PutMapping("/{sizeId}/status")
	public ResponseEntity<SizeResponse> changeStatus(@PathVariable Long sizeId) throws CustomException {
		return new ResponseEntity<>(sizeService.changeStatus(sizeId), HttpStatus.OK);
	}

}
