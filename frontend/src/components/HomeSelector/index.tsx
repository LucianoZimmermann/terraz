import Select from "react-select";

interface HomeSelectorProps {
  context?: any;
}

export default function HomeSelector({ context }: HomeSelectorProps) {
  const handleOptions: any = [
    { label: "Opção 1", value: "1" },
    { label: "Opção 2", value: "2" },
  ];

  return (
    <Select
      options={handleOptions}
      className="homeSelector"
      classNamePrefix="select"
      isClearable
    />
  );
}
