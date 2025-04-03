import { EntityType } from "../../../constant/entity";
import { useEntity } from "../../../context/EntityContext";
import Button from "../../Button";

export default function EntityButtons() {
  const entities = Object.values(EntityType);
  const { setEntity } = useEntity();

  return (
    <section className="entityButtons">
      {entities.map((entity) => (
        <Button
          key={entity}
          content={entity}
          onClick={() => setEntity(entity)}
        />
      ))}
    </section>
  );
}
