package pe.idat.ztore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import pe.idat.ztore.model.DetailsOrder;
import pe.idat.ztore.repository.DetailsOrderRepository;
import pe.idat.ztore.service.DetailsOrderService;

@Service
public class DetailsOrderServiceImpl implements DetailsOrderService{

private final DetailsOrderRepository detailsOrderRepository;
	
	public DetailsOrderServiceImpl(DetailsOrderRepository detailsOrderRepository) {
		this.detailsOrderRepository = detailsOrderRepository;
	}
	@Override
	public DetailsOrder save(DetailsOrder detailsOrder) {
		return detailsOrderRepository.save(detailsOrder);
	}

	@Override
	public DetailsOrder findById(Long id) {
		if(!detailsOrderRepository.existsById(id)) {
			throw new RuntimeException("detailsOrder not found");
		}
		return detailsOrderRepository.findById(id).get();
	}

	@Override
	public List<DetailsOrder> getAll() {
		List<DetailsOrder> detailsOrder = detailsOrderRepository.findAll();
        if (detailsOrder.isEmpty()){
            throw new RuntimeException("No detailsOrder found");
        }
        return detailsOrder;
	}
}
