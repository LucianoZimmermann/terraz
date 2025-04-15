import { PencilIcon, TrashIcon } from "@heroicons/react/24/solid";

export default function ItemActions() {
  return (
    <div className="itemActions">
      <PencilIcon className="pencil" />
      <TrashIcon className="trash" />
    </div>
  );
}
