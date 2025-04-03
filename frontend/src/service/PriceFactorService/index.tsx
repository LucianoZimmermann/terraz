import { PriceFactorDTO } from "../../dto";
import api from "../api";

const PriceFactorService = {
  async getAllPriceFactor(): Promise<PriceFactorDTO[]> {
    const response = await api.get("/price-factors");
    return response.data;
  },

  async getPriceFactorById(id: number): Promise<PriceFactorDTO> {
    const response = await api.get(`/price-factors/${id}`);
    return response.data;
  },
};

export default PriceFactorService;
